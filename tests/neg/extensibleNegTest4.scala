import dotty.records.Extensible

object ExtensibleTest {

  // AndTypes: Discover existing conflicting field in one of the components
  type T1 = Selectable{val a: Int} & Selectable{val b: Int} & Selectable{val c: Int}
  implicitly[Extensible[T1, "b", String]] // error

  // OrTypes: If it is the left case, it isn't extensible so this should be rejected
  type T2 = Selectable{val a: Int} | Selectable{val b: Int}
  implicitly[Extensible[T2, "a", String]] // error

}