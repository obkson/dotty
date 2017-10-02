import dotty.records._

object records_pos_1 {

  def main(args: Array[String]): Unit = {
    // create empty record
    val r = Record()

    // update with new field
    val s = r.update("l", "v")

    // access new field with type
    val l: String = s.l

    println(l) // v
  }

}