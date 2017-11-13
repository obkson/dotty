import dotty.records._

object records_neg_1 {
  val r = Record(l="v")
  val s = Record(l="b")
  val t = r.extend(s) // error: Cannot prove that the record types are disjoint.
}