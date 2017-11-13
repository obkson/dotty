package dotty
package object records {

  @annotation.implicitNotFound("Cannot prove that the record types are disjoint.")
  type DisjointFrom[+S <: Record] = [+R <: Record] => Disjoint[R, S]

  @annotation.implicitNotFound("Cannot prove that the record types are disjoint.")
  trait Disjoint[+R <: Record, +S <: Record]

  object Disjoint {
    def apply[R <: Record, S <: Record]() = new Disjoint[R, S] {}
  }

}