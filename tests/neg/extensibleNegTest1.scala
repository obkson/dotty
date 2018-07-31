import dotty.records.{Extensible, Record}


object ExtensibleTest {
  // Extension type has to be a subtype of existing refinement type member
  class A
  class B extends A
  implicitly[Extensible[Record{val a: String}, "a", Int]] // error
  implicitly[Extensible[Record{val a: B}, "a", A]] // error

  // The Nothing type is the bottom type containing *every* possible field. Must be rejected.
  implicitly[Extensible[Nothing, "a", Int]] // error

  // HKType -- dealias --> RefinedType with conflicting member
  type T[V] = Selectable{val a: V}
  implicitly[Extensible[T[String], "a", Int]] // error
}