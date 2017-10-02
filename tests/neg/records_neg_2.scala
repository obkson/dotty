import dotty.records._

object records_neg_2 {
  // create
  val r = Record("l"->"v").asInstanceOf[Record{val l: String}]

  val f = r.foo // error: value `foo` is not a member of records.Record{l: String}
}