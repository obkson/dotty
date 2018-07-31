import dotty.records.{Extensible, Record}

object ExtensibleTest {
  // AppliedType Function0[Record]. Concrete, but not a subtype of Record.
  implicitly[Extensible[() => Record, "a", Int]] // error

  // ConstantType. Concrete, but not subtype of Record
  implicitly[Extensible["hello", "a", Int]] // error
}