import dotty.records._

trait RecordField[L <: String, V] {
  def label: L
  def value: V
}

object RecordField {
  def apply[V](l: String, v: V) = new RecordField[l.type, V] {
    def label = l
    def value = v
  }
}

object records_pos_8 {

  def getUpdater[L <: String, V, R <: Record](r: R, field: RecordField[L, V])(implicit up: PolymorphicUpdater[R, L, V]) = up
  //def getUpdater2[R <: Record, L <: Label, V](r: R, label: L, value: V)(implicit up: PolymorphicUpdater[R, L#Out, V]) = up

  def main(args: Array[String]): Unit = {

    getUpdater(Record(), RecordField("age", 29))
    //getUpdater2(Record(), Label("age"), 29)
  }
}