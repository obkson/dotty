package dotty.records

import scala.collection.immutable.HashMap

case class Record(_data: Map[String, Any]) extends Selectable {
  def selectDynamic(name: String): Any = _data(name)

  override def toString = _data.keys.toList.sorted
    .map(k => s"$k = ${_data(k)}")
    .mkString("Record{",", ","}")

}

/*
trait Row[L <: HList]

object Row {
  implicit def fieldRow[L <: String, V, T <: HList](implicit ev: Row[T]): Row[:*:[(L, V), T]] = new Row[:*:[(L, V), T]]{}
  implicit def emptyRow: Row[HNil] = new Row[HNil]{}
}
*/



object Record {

  def apply(_data: (String, Any)*) = new Record(_data = HashMap(_data: _*))

  def fromHList[L <: HList](lst: L)(implicit con: HListConverter[L]) = {

    def rowToMap[L <: HList](lst: L): List[(String, Any)] = lst match {
      case :*:((label: String, value), tail) => (label, value) :: rowToMap(tail)
      case _: HNil => Nil
      case _ => throw new Exception("Guru Meditation Error") // `con` is evidence that `lst` is a row, so this can't happen
    }

    new Record(_data = HashMap(rowToMap(lst): _*))
  }

  implicit class RecordOps[R <: Record](val r: R) extends AnyVal {
    def update[L <: String, V](label: L, value: V)(implicit ub: UpperBoundUpdater[R, L, V], poly: PolymorphicUpdater[R, L, V]): ub.Out & poly.Out =
      new Record(r._data + (label -> value)).asInstanceOf[ub.Out & poly.Out]

    def select[L <: String](label: L)(implicit sel: Selector[R]): sel.Out =
      r.selectDynamic(label).asInstanceOf[sel.Out]

    def remove[L <: String](label: L)(implicit rem: Remover[R]): rem.Out =
      new Record(r._data - label).asInstanceOf[rem.Out]

    def toHList(implicit iter: FieldExtractor[R]): HList = r.headOption match {
      case Some(Tuple2(label, value)) => (label, value) :*: r.toHList
      case None => HNil
    }
  }
}