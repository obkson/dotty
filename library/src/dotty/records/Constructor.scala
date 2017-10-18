package dotty.records

class Constructor[P] {
  type Out <: Record
}

object Constructor {
  def apply[P, Out0 <: Record]() = new Constructor {
    type Out = Out0
  }
}