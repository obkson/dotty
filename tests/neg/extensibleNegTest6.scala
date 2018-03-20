import dotty.records.Extensible

object ExtensibleTest {
  // AppliedType Function0[Selectable]. Concrete, but not a subtype of Selectable.
  implicitly[Extensible[() => Selectable, "a", Int]] // error

  // ConstantType. Concrete, but not subtype of Selectable
  implicitly[Extensible["hello", "a", Int]] // error
}