package dotty.records

class Field[L <: String, +V, +S <: Selectable](val label: L, val value: V) {
  override def equals(that : Any) = that match {
    case Field(l, v) => label == l && value == v
    case _ => false
  }
  override def toString = s"($label ->> $value)"
}

object Field {
  def apply[L <: String, V, S <: Selectable](label: L, value: V)(implicit ft: FieldTyper[label.type, V]): Field[label.type, V, ft.Out] = new Field[label.type, V, ft.Out](label, value)
  def unapply(f: Field[String, Any, Selectable]): Option[(String, Any)] = Some((f.label, f.value))
}