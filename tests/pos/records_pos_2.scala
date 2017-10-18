import dotty.records._

object records_pos_2 {

  def main(args: Array[String]): Unit = {
    // create
    val r = Record("l1" -> "v1") // .asInstanceOf[Record {val l1: String}]

    // update with new field
    val s = r.update("l2", 2)

    // access old field
    val l1: String = s.l1
    println(l1) // v1

    // access new field
    val l2: Int = s.l2
    println(l2) // 2
  }
}