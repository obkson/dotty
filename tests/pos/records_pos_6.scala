import dotty.records._

object records_pos_6 {

  def addFooBar[R <: Record](r: R)(implicit ev: Ext[R, Record{val foo: String; val bar: String}]) = {
    val s1 = r ++ Record(foo="foo")
    val s2 = s1 ++ Record(bar="bar") // error: Cannot prove that the record types are disjoint.
    // We only have proof that
    //   R
    // is disjoint from
    //   Record{foo: String, bar: String}

    // Cannot derive that
    //   R & Record{foo: String}
    // is disjoint from
    //   Record{bar: String}

    // Is there some way to design our implicit resolution mechanism so that we can detect this case?

    s2
  }

  def main(args: Array[String]): Unit = {
    // create
    val r = Record()

    // update using polymorphic function
    val s = addFooBar(r)

    println(s.foo) // foo
    println(s.bar) // bar
  }
}