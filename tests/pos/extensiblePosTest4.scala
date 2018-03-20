import dotty.records.Extensible

object ExtensibleTest {

  // AndType
  type T1 = Selectable{val a: Int} & Selectable{val b: Int}
  implicitly[Extensible[T1, "c", Int]]

  // OrType: Regardless of which case we have, it is extensible
  type T2 = Selectable{val a: Int} | Selectable{val b: Int}
  implicitly[Extensible[T2, "c", Int]]

  // Annotated type (gone through dealiasing)
  type T3 = Selectable @transient
  implicitly[Extensible[T3, "a", Int]]

}