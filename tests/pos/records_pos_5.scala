import dotty.records._

object records_pos_5 {

  def addBar[R <: Record](r: R)(implicit ev: Ext[R, Record{val bar: String}]) = r ++ Record(bar="bar")

  def addBarToFoo[R <: Record{val foo: String}](r: R)(implicit ev: Ext[R, Record{val bar: String}]) = {
    println(r.foo) // foo
    val s = addBar(r)
    println(s.foo) // foo
    println(s.bar) // bar
    s
  }

  def main(args: Array[String]): Unit = {
    // create
    val r = Record(foo="foo")

    // update using polymorphic function
    val s = addBarToFoo(r)

    // access old field
    val f: String = s.foo
    // access new field
    val b: String = s.bar

    println(f) // foo
    println(b) // bar
  }
}