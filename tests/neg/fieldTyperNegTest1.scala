import dotty.records.{FieldTyper, Record}

object FieldTyperTest {
  // Cannot derive FieldTyper with invalid Out type member already set
  // (Not only gives error but actually breaks the compiler due to a bug with "implicitNotFound" annotation...)
  implicitly[FieldTyper["foo", String]{ type Out = Record{val bar: Int}}] // error
}