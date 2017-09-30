import dotty.records._

object records_pos_3 {
  // create
  val r = Record("l"->"v1").asInstanceOf[Record{val l1: String}]
  // update with new type for old field
  val s = r.update("l", 1)

  // access the field with new type
  val l: Int = s.l
}