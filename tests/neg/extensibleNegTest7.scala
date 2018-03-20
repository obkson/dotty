import dotty.records.Extensible

object ExtensibleTest {
  // RecType

  // Note: this might be ok, but we currently don't know how to handle recursive types

  type T = Selectable{type M; val a: M}
  implicitly[Extensible[T, "a", T#M]] // error
}