package dotty.records

// NOTE: contra-variant in R
class UpperBoundUpdater[-R <: Record, L <: String, V] {
  type Out <: Record
}

object UpperBoundUpdater {
  def apply[R <: Record, L <: String, V, Out0 <: Record]() = new UpperBoundUpdater[R, L, V] {
    type Out = Out0
  }
}