package dotty.records
import scala.collection.immutable.HashMap

class Record(val _data: HashMap[String, Any]) extends Selectable {

  def selectDynamic(name: String) = _data(name)

  override def equals(that : Any) = that match {
    case Record(d) => _data == d
    case _ => false
  }

  override def toString = s"Record(${_data.map{ case (l, v) => s"$l=$v" }.mkString(", ")})"

}

object Record {

  def apply(args: (String, Any)*) = new Record(HashMap(args: _*))

  def unapply(d: Record): Option[Map[String, Any]] = Some(d._data)

  def +[L <: String, V](l: L, v: V)(implicit ft: FieldTyper[l.type, V], ev: Extensible[Record, l.type, V]): Record & ft.Out = {
    new Record(HashMap((l, v))).asInstanceOf[Record & ft.Out]
  }

  def +[L <: String, V](f: Field[L, V])(implicit ev: Extensible[Record, L, V]) = {
    new Record(HashMap((f.label, f.value))).asInstanceOf[Record & f.Out]
  }
}