import dotty.records._

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
  def record(hlist: H): Record
}

object Converter {

  def hlistToRecord[H <: HList](hlist: H)(implicit conv: Converter[H]) = conv.record(hlist)

  implicit def hnilConverter[H <: HNil]: Converter[H] = new Converter[H] {
    type Out = Record
    def record(hlist: H) = Record()
  }

  implicit def hlistConverter[L <: Singleton & String, V, T <: HList](implicit conv: Converter[T], up: Updater[L, V][conv.Out]): Converter[(L, V) :*: T] = {
    new Converter[(L, V) :*: T] {
      type Out = up.Out
      def record(hlist: (L, V) :*: T) = new Record(conv.record(hlist.tail)._data + hlist.head).asInstanceOf[up.Out]
    }
  }

}

/* *************** MAIN **************** */

object records_pos_7 {

  def main(args: Array[String]): Unit = {
    import Converter._

    val l: ("name", String) :*: ("age", Int) :*: HNil = (("name", "Olle"): ("name", String)) :*: (("age", 29): ("age", Int)) :*: HNil
    val c = hlistConverter["name", String, HNil]
  }
}