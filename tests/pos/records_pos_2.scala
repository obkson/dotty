import dotty.records._

object records_pos_2 {

  def main(args: Array[String]): Unit = {
    // create
    val r = Record()
    val s = Record(l="v")
    val t = r.extend(s)
    println(t) // Record{l = v}
  }
}