import dotty.records._

object records_pos_4 {

  def addField[R <: Record : Updater["l2", Int]](r: R) = {
    val s = r.update("l2", 2)

    // access the new field that we now know is on the record
    val l2: Int = s.l2
    println(l2) // 2

    // return the updated record
    s
  }

  def main(args: Array[String]): Unit = {
    // create
    val r = Record("l1" -> "v1") // .asInstanceOf[Record {val l1: String}]

    // update using polymorphic function
    val s = addField(r)

    // access old field
    val l1: String = s.l1
    println(l1) // "v1"

    // access new field
    val l2: Int = s.l2
    println(l2) // 2
  }
}
