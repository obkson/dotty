package dotty

package object records {

  type Ext[L <: String, -V] = [+S <: Selectable] => Extensible[S, L, V]

  implicit def createStringConverter[L <: String, V](l: L)(implicit ft: FieldTyper[l.type, V]): StringConverter[l.type, V, ft.Out] = new StringConverter[l.type, V, ft.Out](l)

  final class StringConverter[L <: String, V, S <: Selectable](val l: L) extends AnyVal {
    def ->>(v: V): Field[L, V, S] = new Field[L, V, S](l, v)
  }

  implicit class RecordOps[R <: Record](r: R) extends AnyVal {
    def +[L <: String, V](l: L, v: V)(implicit ft: FieldTyper[l.type, V], ev: Extensible[R, l.type, V]) = {
      new Record(r._data + ((l, v))).asInstanceOf[R & ft.Out]
    }

    def +[L <: String, V, S <: Selectable](f: Field[L, V, S])(implicit ev: Extensible[R, L, V]) = {
      new Record(r._data + ((f.label, f.value))).asInstanceOf[R & S]
    }
  }
}