package dotty.records

sealed trait HList

// scalastyle:off
case class :*:[+H, +T <: HList](head: H, tail: T) extends HList {
  override def toString = head match {
    case _: :*:[_, _] => s"($head) :*: $tail"
    case _ => s"$head :*: $tail"
  }
}
// scalastyle:on

sealed trait HNil extends HList

case object HNil extends HNil {
  def :*:[H](head: H): :*:[H, HNil] = new :*:(head, this)
}

object HList {
  implicit class HListOps[L <: HList](val hlist: L) extends AnyVal {
    def :*:[H](head: H): :*:[H, L] = new :*:(head, hlist)
  }
}