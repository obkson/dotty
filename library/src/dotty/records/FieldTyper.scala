package dotty.records

// import annotation.implicitNotFound


// @implicitNotFound("Cannot derive Selectable from ${L} ->> ${V}")
trait FieldTyper[L <: String, V] {
  type Out <: Selectable
}

object FieldTyper {
  type Aux[L <: String, V, Out0 <: Selectable] = FieldTyper[L, V] {
    type Out = Out0
  }
}