package dotty.records

import scala.collection.immutable.HashMap

class Record(val _data: Map[String, Any]) extends Selectable {
  def selectDynamic(name: String): Any = _data(name)

  override def toString = _data.keys.toList.sorted
    .map(k => s"$k = ${_data(k)}")
    .mkString("Record{",", ","}")

  override def equals(that: Any) = that match {
    case r: Record => this._data == r._data
    case _ => false
  }
}

object Record extends Dynamic {
  def create(args: (String, Any)*) = new Record(HashMap(args: _*))
  def applyDynamic(name: String)(args: Any*) = create()
  def applyDynamicNamed(name: String)(args: (String, Any)*) = create(args: _*)

  implicit class RecordOps[R <: Record](val r: R) extends AnyVal {
    def extend[S <: Record](s: S)(implicit ev: Disjoint[R, S]): R & S =
      new Record(r._data ++ s._data).asInstanceOf[R & S]
  }
}