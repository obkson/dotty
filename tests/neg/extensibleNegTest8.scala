import dotty.records.{Extensible, Record}

object ExtensibleTest {
  // Refined type alias

  def foo[R <: Record](r: R) = {
    type S = R { val b: String }
    implicitly[Extensible[S, "a", String]] // error
  }

}