import dotty.records._

object records_pos_6 {

  def addBar[R <: Record](r: R)(implicit up1: Updater["bar", String][R], up2: UpperBoundUpdater[R, "bar", String]) = r.update("bar", "bar")

  def addBarToFoo[F <: Record{val foo: String}](r: F)(implicit up2: Updater["bar", String][F]) = {
    val s = addBar(r)
    val f = s.foo
    val b = s.bar
    println(f) // foo
    println(b) // bar
    s
  }

  def main(args: Array[String]): Unit = {
    def getA() = List("a", "b", "c").mkString
    // create
    val r = Record(a=getA(), foo="foo")

    val s = addBarToFoo(r)

    val a = s.a
    val f = s.foo
    val b = s.bar

    println(a) // abc
    println(f) // foo
    println(b) // bar
  }
}