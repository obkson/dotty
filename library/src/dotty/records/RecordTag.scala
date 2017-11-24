package dotty.records

import scala.reflect.ClassTag

class RecordTag[R <: Record](val fields: List[(String, ClassTag[_])])

object RecordTag {
  def apply[R <: Record](fields: (String, ClassTag[_])*) = new RecordTag[R](fields.toList)
}

/*
class RecordTag[R <: Record](fields: String*) {
  def labels = fields.toList
}

object RecordTag {
  def apply[R <: Record](fields: String*) = new RecordTag[R](fields: _*)
}
*/