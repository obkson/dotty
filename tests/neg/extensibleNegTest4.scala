import dotty.records.{Extensible, Record}

object ExtensibleTest {

  // AndTypes: Discover existing conflicting field in one of the components
  type T1 = Record{val a: Int} & Record{val b: Int} & Record{val c: Int}
  implicitly[Extensible[T1, "b", String]] // error

  // OrTypes: If it is the left case, it isn't extensible so this should be rejected
  type T2 = Record{val a: Int} | Record{val b: Int}
  implicitly[Extensible[T2, "a", String]] // error

}