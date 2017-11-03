import dotty.records._

object records_pos_0 {

  def addFoo[R <: Record : Updater["foo", String]](r: R) = r.update("foo", "Foo")

  def main(args: Array[String]): Unit = {
    // create
    // val r = Record("a" -> "A", "nested" -> Record("foo" -> "foo"))
    // val r = Record(("a", "A"), "b"->"B", ("c"-> Record("d"->"D")), "2" -> 23)
    val r = Record("a"->"A")

    // typed access
    val a = r.a
    /*
    val b = r.b
    val c = r.c
    val d = r.c.d
    val two = r.`2`

    val s = addFoo(r)
    */
    println(r)
    println(a) // A

    // val (foo, bar) = r.project("foo", "bar")


    List[Any]("hej", 123) match {
      case (foo: String) :: (bar: Int) :: Nil => {
        println(s"ok! $foo, $bar")
      }
      case _ => {
        println(":(")
      }
    }

    /*
    println(b) // B
    println(c)
    println(d) // D
    println(two) // 23

    println(s)
    println(s.foo) // Foo
  */
  }
}