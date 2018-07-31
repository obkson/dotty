import dotty.records.{Extensible, Record}

object ExtensibleTest {
  // No lower bound
  type T1 <: Record{val foo: Any}
  implicitly[Extensible[T1, "a", Int]]  // error

  // Lower bound without lower bound.
  type L <: Record{val foo: Any}
  type T2 >: L <: Record
  implicitly[Extensible[T2, "a", Int]] // error

  // Existential type -> Type Bound without lower
  implicitly[Extensible[_, "a", Int]] // error

}