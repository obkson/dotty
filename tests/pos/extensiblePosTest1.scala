import dotty.records.{Extensible, Record}

object ExtensibleTest {
  // TypeRef -> ClassInfo or RefinedType

  // This is ok, since we have a concrete ClassInfo
  implicitly[Extensible[Record, "a", Int]]

  // This too, since the field "a" is lacking.
  implicitly[Extensible[Record{val foo: Any}, "a", Int]]

  // This should be ok, since we update with a value of a subtype of existing
  class A
  class B extends A
  implicitly[Extensible[Record{val a: Int}, "a", Int]]
  implicitly[Extensible[Record{val a: A}, "a", B]]

}