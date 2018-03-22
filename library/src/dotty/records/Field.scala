package dotty.records

class Field[L <: String, V](val label: L, val value: V) {
  type Out <: Selectable

  override def equals(that : Any) = that match {
    case Field(l, v) => label == l && value == v
    case _ => false
  }
  override def toString = s"($label ->> $value)"
}

object Field {
  def apply[L <: String, V](label: L, value: V)(implicit ft: FieldTyper[label.type, V]): Field[label.type, V]{type Out = ft.Out} = new Field[label.type, V](label, value){ type Out = ft.Out }
  def unapply(f: Field[String, Any]): Option[(String, Any)] = Some((f.label, f.value))
}