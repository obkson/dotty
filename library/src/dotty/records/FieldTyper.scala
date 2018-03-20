package dotty.records

import annotation.implicitNotFound

@implicitNotFound("Cannot derive Selectable from ${L} ->> ${V}")
sealed trait FieldTyper[L <: String, V] {
  type Out <: Selectable
}

object FieldTyper {
  def apply[L <: String, V, S <: Selectable] = new FieldTyper[L, V]{ type Out = S }
}