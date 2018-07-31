import dotty.records.{Extensible, Record}

object ExtensibleTest {
  // RecType

  // Note: this might be ok, but we currently don't know how to handle recursive types

  type T = Record{type M; val a: M}
  implicitly[Extensible[T, "a", T#M]] // error
}