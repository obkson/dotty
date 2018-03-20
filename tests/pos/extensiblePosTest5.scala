import dotty.records.Extensible

object ExtensibleTest {
  // Variance

  class T
  class A
  class B extends A
  // To make sure the below Extensibles are derived directly from this one, we provide a "false" Extensible here.
  // Thus, if the extensibles below has to be synthesized instead of relying on subtyping, this test will fail.
  implicit val e1: Extensible[Selectable{val a: T}, "a", A] = new Extensible[Selectable{val a: T}, "a", A]{}

  // covariant in S
  implicitly[Extensible[Selectable, "a", A]]
  // contravariant in V
  implicitly[Extensible[Selectable{val a: T}, "a", B]]
  // check both
  implicitly[Extensible[Selectable, "a", A]]
}