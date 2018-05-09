import dotty.records._

object ExtensibleTest {
  // TypeBounds

  // The lower bound guarantees T1 to only contain {foo: Any}
  type T1 >: Selectable{val foo: Any} <: Selectable
  implicitly[Extensible[T1, "a", Int]]

  type T2 >: T1 <: Selectable
  implicitly[Extensible[T2, "a", Int]]

  // T3#a is a super type of B, so the new value of type B has a guaranteed subtype
  class A
  class B extends A
  type T3 >: Selectable{val a: B} <: Selectable
  implicitly[Extensible[T3, "a", B]]

}