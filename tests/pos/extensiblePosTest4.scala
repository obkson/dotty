import dotty.records.{Extensible, Record}

object ExtensibleTest {

  // AndType
  type T1 = Record{val a: Int} & Record{val b: Int}
  implicitly[Extensible[T1, "c", Int]]

  // OrType: Regardless of which case we have, it is extensible
  type T2 = Record{val a: Int} | Record{val b: Int}
  implicitly[Extensible[T2, "c", Int]]

  // Annotated type (gone through dealiasing)
  type T3 = Record @transient
  implicitly[Extensible[T3, "a", Int]]

}