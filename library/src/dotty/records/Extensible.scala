package dotty.records

import annotation.implicitNotFound

@implicitNotFound("Cannot prove that ${S} is extensible with ${L} ->> ${V}")
trait Extensible[+S <: Selectable, L <: String, -V]

object Extensible {
  def apply[S <: Selectable, L <: String, V] = new Extensible[S, L, V]{}
}