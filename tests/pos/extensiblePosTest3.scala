import dotty.records.Extensible

abstract class ExtensibleTest {
  // TermRef -> ClassInfo or RefinedType

  val r: Selectable
  implicitly[Extensible[r.type, "a", Int]]

  val s: Selectable{val a: Int}
  implicitly[Extensible[s.type, "a", Int]]
}