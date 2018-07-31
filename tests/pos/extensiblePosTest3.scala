import dotty.records.{Extensible, Record}

abstract class ExtensibleTest {
  // TermRef -> ClassInfo or RefinedType

  val r: Record
  implicitly[Extensible[r.type, "a", Int]]

  val s: Record{val a: Int}
  implicitly[Extensible[s.type, "a", Int]]
}