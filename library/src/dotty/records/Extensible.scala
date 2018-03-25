package dotty.records

import annotation.implicitNotFound

@implicitNotFound("Cannot prove that ${S} is extensible with ${L} ->> ${V}")
trait Extensible[+S <: Selectable, L <: String, -V] {
  type Out <: Selectable
}

object Extensible {
  def apply[S <: Selectable, L <: String, V, R <: Selectable] = new Extensible[S, L, V]{ type Out = R}
}