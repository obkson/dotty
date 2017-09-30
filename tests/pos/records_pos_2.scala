import dotty.records._

object records_pos_2 {
  // create
  val r = Record("l1"->"v1").asInstanceOf[Record{val l1: String}]
  // update with new field
  val s = r.update("l2", 2)

  // access old field
  val l1: String = s.l1

  // access new field
  val l2: Int = s.l2
}