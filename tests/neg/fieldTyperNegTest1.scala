import dotty.records.FieldTyper

object FieldTyperTest {
  // Cannot derive FieldTyper with invalid Out type member already set
  // (Not only gives error but actually breaks the compiler due to a bug with "implicitNotFound" annotation...)
  implicitly[FieldTyper["foo", String]{ type Out = Selectable{val bar: Int}}] // error
}