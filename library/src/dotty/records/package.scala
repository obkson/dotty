package dotty

import scala.annotation.implicitNotFound

package object records {

  @implicitNotFound("Cannot prove extensibility")
  type Ext[L <: String, -V] = [+S <: Selectable] => Extensible[S, L, V]

  implicit def mkLabelOps[L <: String](l: L): LabelOps[l.type] = new LabelOps[l.type](l)

  final class LabelOps[L <: String](val l: L) extends AnyVal {
    def ->>[V](v: V): Field[L, V] = new Field[L, V](l, v)
  }

}