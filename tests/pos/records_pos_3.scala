import dotty.records._

object records_pos_3 {

  def main(args: Array[String]): Unit = {
    // create
    val r = Record(l="v1")

    // update with new type for old field
    val s = r.update("l", 1)

    // access the field with new type
    val l: Int = s.l
    println(l) // 1
  }
}