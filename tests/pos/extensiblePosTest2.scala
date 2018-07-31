import dotty.records.{Extensible, Record}

object ExtensibleTest {
  // TypeBounds

  // The lower bound guarantees T1 to only contain {foo: Any}
  type T1 >: Record{val foo: Any} <: Record
  implicitly[Extensible[T1, "a", Int]]

  type T2 >: T1 <: Record
  implicitly[Extensible[T2, "a", Int]]

  // T3#a is a super type of B, so the new value of type B has a guaranteed subtype
  class A
  class B extends A
  type T3 >: Record{val a: B} <: Record
  implicitly[Extensible[T3, "a", B]]

}