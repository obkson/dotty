import dotty.records._

object records_neg_2 {
  val r = Record(l="v")
  val f = r.foo // error: value `foo` is not a member of records.Record{l: String}
}