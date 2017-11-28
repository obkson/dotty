import dotty.records._

object records_pos_3 {

  def addFoo[R <: Record : Extender[Record{val foo: String}]](r: R) = {
    val t = r ++ Record(foo="foo")
    println(t.foo) // foo
    t
  }

  def main(args: Array[String]): Unit = {
    // create
    val r = Record()

    // update using polymorphic function
    val s = addFoo(r)

    // access new field
    val f: String = s.foo

    println(f) // foo
  }
}