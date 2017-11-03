import dotty.records._

object hlist_pos_0 {

  def recur(hl: HList): String = hl match {
    case :*:(head, tail) => head match {
      case i: Int => s"Int($i)" + " :*: " + recur(tail)
      case s: String => s"String($s)" + " :*: " + recur(tail)
      case x => s"Unknown($x)" + " :*: " + recur(tail)
    }
    case _: HNil => "HNil"
  }

  def main(args: Array[String]): Unit = {
    // create
    val l = 1 :*: ("two" :*: 3 :*: HNil) :*: HNil
    println(l)
    println(recur(l))
  }
}