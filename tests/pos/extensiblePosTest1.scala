import dotty.records.Extensible

object ExtensibleTest {
  // TypeRef -> ClassInfo or RefinedType

  // This is ok, since we have a concrete ClassInfo
  implicitly[Extensible[Selectable, "a", Int]]

  // This too, since the field "a" is lacking.
  implicitly[Extensible[Selectable{val foo: Any}, "a", Int]]

  // This should be ok, since we update with a value of a subtype of existing
  class A
  class B extends A
  implicitly[Extensible[Selectable{val a: Int}, "a", Int]]
  implicitly[Extensible[Selectable{val a: A}, "a", B]]

}