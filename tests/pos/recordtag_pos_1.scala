import dotty.records._

object recordtag_pos_1 {

  def greeting(r: Record) = {
    r.safeCast[Record{val name: String; val age: Int}] match {
      case Some(s) =>
        s"Hello, ${s.age}-year-old ${s.name}."
      case None => r.safeCast[Record{val name: String}] match {
        case Some(s) =>
          s"Hello, time-less ${s.name}"
        case None =>
          s"Hello, stranger"
      }
    }
  }

  def main(args: Array[String]): Unit = {

    val r = Record(name="Olle", age=29)


    val s = Record(name="Philipp")
    val t = Record(foo="bar")
    val u = Record(name="Cthulhu", age="infinite")
    println(greeting(r))
    println(greeting(s))
    println(greeting(t))
    println(greeting(u))

  }
}