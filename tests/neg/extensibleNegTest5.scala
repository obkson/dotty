import dotty.records.{Extensible, Record}

abstract class ExtensibleTest {
  // Make sure that the widening from a TermRef to Record doesn't also get rid of refinement
  val r: Record{val a: String}
  implicitly[Extensible[r.type, "a", Int]] // error
}