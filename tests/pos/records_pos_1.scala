import dotty.records._

object records_pos_1 {

  def main(args: Array[String]): Unit = {
    // create two records
    val r = Record(a="a")
    val s = Record(b="b")

    // Extend
    val t = r.extend(s)

    // access new field with type
    val a: String = t.a
    val b: String = t.b

    println(a) // a
    println(b) // b
  }

}