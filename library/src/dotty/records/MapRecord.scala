package dotty.records

class MapRecord(val _data: Map[String, Any]) extends Record {

  def selectDynamic(name: String) = _data(name)

  def updated(name: String, value: Any) = new MapRecord(_data.updated(name, value))

  override def equals(that : Any) = that match {
    case MapRecord(d) => _data == d
    case _ => false
  }

  override def toString = s"Record(${_data.map{ case (l, v) => s"$l=$v" }.mkString(", ")})"
}

object MapRecord {

  def apply() = new MapRecord(Map[String, Any]()).asInstanceOf[Record]

  def unapply(d: MapRecord): Option[Map[String, Any]] = Some(d._data)

}