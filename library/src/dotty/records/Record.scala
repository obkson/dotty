package dotty.records

import scala.collection.immutable.HashMap

class Record(protected val _data: Map[String, Any]) extends Selectable {
  def selectDynamic(name: String): Any = _data(name)

  override def toString: String = _data.keys.toList.sorted
    .map(k => s"$k = ${_data(k)}")
    .mkString("Record{",", ","}")

  override def equals(that: Any): Boolean = that match {
    case r: Record => this._data == r._data
    case _ => false
  }
}

object Record extends Dynamic {
  def create(args: (String, Any)*): Record = new Record(HashMap(args: _*))
  def applyDynamic(name: String)(args: Any*): Record = {
    // compiler support to check that `name == "apply"`
    create()
  }
  def applyDynamicNamed(name: String)(args: (String, Any)*): Record = {
    // compiler support to
    // - check that `name == "apply"`, and
    // - cast the returned record
    create(args: _*)
  }

  def labels[R <: Record]: List[String] = List()

  implicit class RecordOps[R <: Record](val r: R) extends AnyVal {
    def ++[S <: Record](s: S)(implicit ev: Ext[R, S]): R & S =
      new Record(r._data ++ s._data).asInstanceOf[R & S]

    def safeCast[S <: Record]: Option[S] = {
      if (Record.labels[S].forall(l => !r._data.contains(l)))
        Some(r.asInstanceOf[S])
      else
        None
    }

    def labels(): List[String] = Record.labels[R]
    def values(): List[Any] = Record.labels[R].map(l => r._data(l))
  }
}