package dotty.records

import annotation.implicitNotFound

@implicitNotFound("Cannot prove that ${S} is extensible with ${L} ->> ${V}")
trait Extensible[+S <: Record, L <: String, -V]