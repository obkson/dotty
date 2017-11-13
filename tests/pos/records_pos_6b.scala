import dotty.records._

object records_pos_6b {

  // Here the chain of updaters is completely abstract, but that is ok. We will not be forced to calculate anything here.
  // They are just accepted "as is".
  def polyscope[R <: Record](r: R)(implicit p1: PolymorphicUpdater[R, "foo", String], p2: PolymorphicUpdater[p1.Out, "bar", String]): p2.Out = {

    val f = r.update("foo", "foo")
    println(f.foo)
    val b = f.update("bar", "bar")
    println(b.foo)
    println(b.bar)
    b
  }

  def main(args: Array[String]): Unit = {

    val r = Record()


    val s = polyscope(r)
    // Here we need
    //  - PolymorphicUpdater[Record, "foo", String]                                       => Record{foo: String}
    //  - PolymoprhicUpdater[PolymorphicUpdater[Record, "foo", String], "bar", String]    => Record{foo: String, bar: String}

    println(s)
  }
}