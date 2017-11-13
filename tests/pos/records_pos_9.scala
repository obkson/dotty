import dotty.records._

/* ************ Field  ************** */
trait RecordField {
  type Label <: Singleton & String
  def label: Label
  type Value
  def value: Value
}

object RecordField {
  def apply[V](l: String, v: V) = new RecordField {
    type Label = l.type
    def label = l
    type Value = V
    def value = v
  }
}

/* ************ HList ************** */
sealed trait HList

// scalastyle:off
case class :*:[+H, +T <: HList](head: H, tail: T) extends HList {
  override def toString = head match {
    case _: :*:[_, _] => s"($head) :*: $tail"
    case _ => s"$head :*: $tail"
  }
}
// scalastyle:on

sealed trait HNil extends HList {
  def :*:[H](head: H) = new :*:(head, this)
}

case object HNil extends HNil

object HList {
  implicit class HListOps[L <: HList](val hlist: L) extends AnyVal {
    def :*:[H](head: H): :*:[H, L] = new :*:(head, hlist)
  }
}

/* ************* Converter Typeclass **************** */

trait Converter[-H <: HList] {
  type Out <: Record
  def record(hlist: H): Out
}

object Converter {

  def hlistToRecord[H <: HList](hlist: H)(implicit conv: Converter[H]) = conv.record(hlist)

  implicit def hnilConverter[H <: HNil]: Converter[H] = new Converter[H] {
    type Out = Record
    def record(hlist: H) = Record()
  }

  implicit def hlistConverter[F <: RecordField, T <: HList](implicit conv: Converter[T], up: Updater[F, String][conv.Out]): Converter[RecordField :*: T] = {
    new Converter[RecordField :*: T] {
      type Out = up.Out
      def record(hlist: RecordField :*: T) = new Record(conv.record(hlist.tail)._data + (hlist.head.label -> hlist.head.value)).asInstanceOf[up.Out]
    }
  }

}

/* *************** MAIN **************** */

object records_pos_9 {

  def main(args: Array[String]): Unit = {
    import Converter._

    val l = RecordField("name", "Olle") :*: RecordField("age", 29) :*: HNil
    val r = hlistToRecord(l)
  }
}