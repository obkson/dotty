package dotty.records

abstract class Record extends Selectable {
  def updated(name: String, value: Any): Record
}

object Record {
  def apply() = new MapRecord(Map[String, Any]()).asInstanceOf[Record]

  def apply[
    L1 <: String, V1, F1 <: Record]
  (f1: Field[L1, V1])
  (implicit erased ft1: FieldTyper.Aux[L1, V1, F1])
  : F1 = {
    new MapRecord(Map((f1.label, f1.value))).asInstanceOf[F1]
  }

  def apply[V1, F1 <: Record]
  (l: String, v: V1)
  (implicit erased ft1: FieldTyper.Aux[l.type, V1, F1])
  : F1 = {
    new MapRecord(Map((l, v))).asInstanceOf[F1]
  }

  def apply[
    L1 <: String, V1, F1 <: Record,
    L2 <: String, V2, F2 <: Record]
  (f1: Field[L1, V1], f2: Field[L2, V2])
  (implicit erased ft1: FieldTyper.Aux[L1, V1, F1],
                   ft2: FieldTyper.Aux[L2, V2, F2],
                   ev2: Extensible[F1, L2, V2])
  : F1 & F2 = {
    new MapRecord(Map((f1.label, f1.value), (f2.label, f2.value))).asInstanceOf[F1 & F2]
  }

  inline def guard[T](r: T) = r

}