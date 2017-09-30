import dotty.records._

object records_pos_1 {
  // create
  val r = Record("l"->"v").asInstanceOf[Record{val l: String}]
  // typed access
  val l: String = r.l
}