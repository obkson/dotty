package dotty.records

import scala.reflect.ClassTag
import scala.collection.immutable.HashMap

class Record(val _data: Map[String, Any]) extends Selectable {
  def selectDynamic(name: String): Any = _data(name)

  override def toString: String = _data.keys.toList.sorted
    .map(k => s"$k = ${_data(k)}")
    .mkString("Record{",", ","}")

  override def equals(that: Any): Boolean = that match {
    case r: Record => this._data == r._data
    case _ => false
  }
}

object Record extends Dynamic {
  def create(args: (String, Any)*): Record = new Record(HashMap(args: _*))

  def applyDynamic(name: String)(args: Any*): Record = {
    // compiler support to check that `name == "apply"`
    create()
  }

  def applyDynamicNamed(name: String)(args: (String, Any)*): Any = {
    // compiler support to
    // - check that `name == "apply"`, and
    // - cast the returned record
    create(args: _*)
  }

  implicit class RecordOps[R <: Record](val r: R) extends AnyVal {
    def ++[S <: Record](s: S)(implicit ev: Ext[R, S]): R & S =
      new Record(r._data ++ s._data).asInstanceOf[R & S]

    def safeCast[S <: Record](implicit rtag: RecordTag[S]): Option[S] = {
      // println(s"checking if $r can be casted to ${rtag.fields}")

      def fieldOk(lbl: String, tag: ClassTag[_] | RecordTag[_] | SequenceTag[_]) = r._data.get(lbl) match {
        case Some(v) => valueOk(v, tag)
        case None => false
      }
      def valueOk(v: Any, tag: ClassTag[_] | RecordTag[_] | SequenceTag[_]): Boolean = tag match {
        case ct: ClassTag[_] => ctOk(v, ct)
        case rt: RecordTag[_] => rtOk(v, rt)
        case st: SequenceTag[_] => stOk(v, st)
      }
      def ctOk(v: Any, tag: ClassTag[_]): Boolean = tag.unapply(v).isDefined
      def rtOk(v: Any, tag: RecordTag[_]): Boolean = v match {
        case rec: Record => rec.safeCast(tag).isDefined
        case _ => false
      }
      def stOk(v: Any, tag: SequenceTag[_]): Boolean = v match {
        case s: Seq[Any] => tag.seq.unapply(s).isDefined && s.forall(e => valueOk(e, tag.elem))
        case _ => false
      }
      if (rtag.fields.forall(fld => fieldOk(fld._1, fld._2)))
        Some(r.asInstanceOf[S])
      else
        None
    }
  }
}
