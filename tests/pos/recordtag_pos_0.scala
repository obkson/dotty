import dotty.records._

import scala.reflect.ClassTag

object recordtag_pos_0 {

  def getLabels[R <: Record](r: R)(implicit tag: RecordTag[R]): List[String] = tag.fields.map(_._1)

  def getFields[R <: Record](r: R)(implicit tag: RecordTag[R]): List[(String, ClassTag[_])] = tag.fields

  def main(args: Array[String]): Unit = {

    val r = Record(a="a", b=2, c=List("c"))

    val labels = getLabels(r)
    val fields = getFields(r)

    println(labels) // List(a, b, c)
    println(fields) // List((a,java.lang.String), (b,Int), (c,scala.collection.immutable.List))

  }
}