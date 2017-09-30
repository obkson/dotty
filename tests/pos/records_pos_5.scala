import dotty.records._

object records_pos_5 {

  def addField[R <: Record{val l: String}](r: R)(implicit up: Updater[R, "l", Int]): up.Out = {
    // access old field
    val l1: String = r.l
    println(l1)

    // update with new type
    val s = r.update("l", 1)

    // access the updated field
    val l2: Int = s.l
    println(l2) // 1

    // return the updated record
    s
  }

  def main(args: Array[String]): Unit = {
    // create
    val r = Record("l" -> "one").asInstanceOf[Record {val l: String}]

    // update using polymorphic function to change type of `l` from String to Int
    val s = addField(r)

    // access field with new type
    val l: Int = s.l
    println(l) // 1

    // acces another field and checkout the error msg!
    // println(s.foo)
  }
}