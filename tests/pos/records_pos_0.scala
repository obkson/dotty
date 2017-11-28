import dotty.records._
import dotty.records.RecordExtensions._

object records_pos_0 {

  def main(args: Array[String]): Unit = {
    // create
    val r = Record(a="A")

    // typed access
    val a = r.a

    println(r) // Record{a = A}
    println(a) // A
  }
}