import dotty.records._

object records_pos_3 {

  def addBar[R <: Record : DisjointFrom[Record{val bar: "bar"}]](r: R) = {
    val s = Record(bar="bar")
    val t = r.extend(s)
    println(t.bar) // bar
    t
  }

  def main(args: Array[String]): Unit = {
    // create
    val r = Record(foo="foo")

    // update using polymorphic function
    val s = addBar(r)

    // access old field
    val f: String = s.foo
    // access new field
     val b: String = s.bar

    println(f) // foo
    println(b) // bar
  }
}