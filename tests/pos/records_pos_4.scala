import dotty.records._

object records_pos_4 {

  def addFoo[R <: Record](r: R)(implicit ev: Ext[R, Record{val foo: String; val bar: String}]) = {
    val t = r ++ Record(foo="foo")
    println(t.foo) // foo
    t
  }

  def main(args: Array[String]): Unit = {
    // create
    val r = Record() ++ Record(foo="foo") ++ Record(bar="bar")

    // update using polymorphic function
    val s = addFoo(r)

    // access new field
    val f: String = s.foo

    println(f) // foo
  }
}