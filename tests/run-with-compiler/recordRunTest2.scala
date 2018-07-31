import dotty.records._

object RecordRunTest2 {

  def addOne[R <: Record : Ext["a", Int]](r: R) = r + ("a"->>1)

  def addMulti[R <: Record : Ext["a", Int] : Ext["b", String]](r: R) = r + ("a"->>1) + ("b"->>"two")

  def main(args: Array[String]): Unit = {

    val r = Record()
    val s = addOne(r)
    val t = addMulti(r)

    println(r)
    println(s)
    println(t)
    println(t.a)
    println(t.b)
  }
}