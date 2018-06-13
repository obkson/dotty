package dotty

import scala.annotation.implicitNotFound

package object records {

  @implicitNotFound("Cannot prove extensibility")
  type Ext[L <: String, -V] = [+S <: Record] => Extensible[S, L, V]

  implicit def mkLabelOps[L <: String](l: L): LabelOps[l.type] = new LabelOps[l.type](l)

  final class LabelOps[L <: String](val l: L) extends AnyVal {
    def ->>[V](v: V): Field[L, V] = new Field[L, V](l, v)
  }

  implicit final class RecordOps[R <: Record](r: R) extends AnyVal {

    def +[L <: String, V, F <: Record](l: L, v: V)(implicit erased ev: Extensible[R, l.type, V], ft: FieldTyper.Aux[l.type, V, F]) = {
      r.updated(l, v).asInstanceOf[R & F]
    }

    def +[L <: String, V, F <: Record](f: Field[L, V])(implicit erased ev: Extensible[R, L, V], ft: FieldTyper.Aux[L, V, F]) = {
      r.updated(f.label, f.value).asInstanceOf[R & F]
    }

    /*
    def +[L <: String, V](l: L, v: V)(implicit ft: FieldTyper[l.type, V], ev: Extensible[R, l.type, V]): R & ft.Out = {
      r.updated(l, v).asInstanceOf[R & ft.Out]
    }

    def +[L <: String, V](f: Field[L, V])(implicit ft: FieldTyper[L, V], ev: Extensible[R, L, V]): R & ft.Out = {
      r.updated(f.label, f.value).asInstanceOf[R & ft.Out]
    }
    */
  }

}