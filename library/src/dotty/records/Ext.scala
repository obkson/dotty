package dotty.records

sealed trait UnsafeExt[+R <: Record, +S <: Record]

trait Ext[+R <: Record, S <: Record] extends UnsafeExt[R, S]

object Ext {
  def apply[R <: Record, S <: Record]() = new Ext[R, S] {}
}