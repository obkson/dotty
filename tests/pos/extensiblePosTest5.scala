import dotty.records.{Extensible, Record}

object ExtensibleTest {
  // Variance

  class T
  class A
  class B extends A
  // To make sure the below Extensibles are derived directly from this one, we provide a "false" Extensible here.
  // Thus, if the extensibles below has to be synthesized instead of relying on subtyping, this test will fail.
  implicit val e1: Extensible[Record{val a: T}, "a", A] = new Extensible[Record{val a: T}, "a", A]{}

  // covariant in S
  implicitly[Extensible[Record, "a", A]]
  // contravariant in V
  implicitly[Extensible[Record{val a: T}, "a", B]]
  // check both
  implicitly[Extensible[Record, "a", A]]
}