package dotty.records

// NOTE: invariant in R
class PolymorphicUpdater[R <: Record, L <: String, V] {
  type Out <: Record
}

object PolymorphicUpdater {
  def apply[R <: Record, L <: String, V, Out0 <: Record]() = new PolymorphicUpdater[R, L, V] {
     type Out = Out0
  }
}
