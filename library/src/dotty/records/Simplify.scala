package dotty.records

class Simplify[T <: Selectable] {
  type Out <: Selectable
}

object Simplify {
  type Aux[T <: Selectable, Out0 <: Selectable] = Simplify[T] {
    type Out = Out0
  }
}
