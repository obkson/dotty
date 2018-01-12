import dotty.records._

trait TypeClass[A <: Record] {
  def print(): Unit
}

object recordtag_pos_4 {

  implicit def getImplementation[R <: Record](implicit tag: RecordTag[R]): TypeClass[R] = new TypeClass[R]{
    def print() = println(tag.fields)
  }

  def doStuff[R <: Record](implicit tc: TypeClass[R]) = {
    tc.print()
  }

  def main(args: Array[String]): Unit = {

    doStuff[Record{val foo: Int; val bar: String}] // List((foo,Int), (bar,java.lang.String))

  }
}