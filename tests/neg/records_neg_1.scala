import dotty.records._

object records_neg_1 {
  val r = Record("l"->"v").asInstanceOf[Record{val l: String}]

  val l: Int = r.l // error: found: String, required: Int
}