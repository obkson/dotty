package dotty.records

// import annotation.implicitNotFound


// @implicitNotFound("Cannot derive Record from ${L} ->> ${V}")
trait FieldTyper[L <: String, V] {
  type Out <: Record
}

object FieldTyper {
  type Aux[L <: String, V, Out0 <: Record] = FieldTyper[L, V] {
    type Out = Out0
  }
}