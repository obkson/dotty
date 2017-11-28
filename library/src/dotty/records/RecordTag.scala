package dotty.records

import scala.reflect.ClassTag

class RecordTag[R <: Record](val fields: List[(String, ClassTag[_] | RecordTag[_] | SequenceTag[_])])

object RecordTag {
  def apply[R <: Record](fields: (String, ClassTag[_] | RecordTag[_] | SequenceTag[_])*) = new RecordTag[R](fields.toList)
}

class SequenceTag[S <: Seq[_]](val seq: ClassTag[S], val elem: ClassTag[_] | RecordTag[_] | SequenceTag[_])

object SequenceTag {
  def apply[S <: Seq[_]](seq: ClassTag[S], elem: ClassTag[_] | RecordTag[_] | SequenceTag[_]) = new SequenceTag[S](seq, elem)
}