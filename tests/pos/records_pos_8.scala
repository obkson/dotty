import dotty.records._

object records_pos_8 {

  def main(args: Array[String]): Unit = {
    // create
    val r = Record.foo(bar="baz")
    val s = Record.bar
  }
}