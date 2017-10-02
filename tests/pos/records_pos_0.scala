import dotty.records._

object records_pos_0 {

  def main(args: Array[String]): Unit = {
    // create
    val r = Record("l" -> "v").asInstanceOf[Record {val l: String}]

    // typed access
    val l: String = r.l

    println(l) // v
  }
}