import dotty.records.Extensible

abstract class ExtensibleTest {
  // Make sure that the widening from a TermRef to Selectable doesn't also get rid of refinement
  val r: Selectable{val a: String}
  implicitly[Extensible[r.type, "a", Int]] // error
}