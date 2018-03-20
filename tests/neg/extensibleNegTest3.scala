import dotty.records.Extensible

object ExtensibleTest {
  // No lower bound
  type T1 <: Selectable{val foo: Any}
  implicitly[Extensible[T1, "a", Int]]  // error

  // Lower bound without lower bound.
  type L <: Selectable{val foo: Any}
  type T2 >: L <: Selectable
  implicitly[Extensible[T2, "a", Int]] // error

  // Existential type -> Type Bound without lower
  implicitly[Extensible[_, "a", Int]] // error

}