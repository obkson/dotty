package dotty.records

trait HListConverter[L <: HList] {
  type Out <: Record
}

object HListConverter {
  def apply[L <: HList, Out0 <: Record]() = new HListConverter[L] {
    type Out = Out0
  }
}