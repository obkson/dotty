package dotty.records

class Field[L <: String, +V](val label: L, val value: V) {

  override def equals(that : Any) = that match {
    case Field(l, v) => label == l && value == v
    case _ => false
  }

  override def toString = s"($label ->> $value)"
}

object Field {
  def apply[L <: String, V](label: L, value: V): Field[label.type, V] = new Field[label.type, V](label, value)
  def unapply(f: Field[String, Any]): Option[(String, Any)] = Some((f.label, f.value))
}