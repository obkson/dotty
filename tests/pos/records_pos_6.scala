import dotty.records._

object records_pos_6 {


  def addBar[R <: Record](r: R)(implicit p2: PolymorphicUpdater[R, "bar", String]): p2.Out = {
    val s = r.update("bar", "bar")
    s
  }

  def addBarToFoo[R <: Record{val foo: String}](r: R)(implicit p1: PolymorphicUpdater[R, "bar", String]): p1.Out = {
    println(r.foo)
    val s = addBar(r) // how can we force calculation here? If we add an UpperBoundUpdater to addBar, then we will loose calculation there instead.
                      // We are merely deferring the problem by introducing the UpperBoundUpdater to the RecordOps updaters.
                      // To introduce another function layer (addBar), we would have to create a 3rd updater class to force calculation etc...
    println(s.foo)
    println(s.bar)
    s
  }

  def main(args: Array[String]): Unit = {

    val r = Record(foo="foo")
    val s = addBarToFoo(r)

    println(s)
  }
}