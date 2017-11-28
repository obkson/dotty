package dotty.records

object RecordExtensions extends Phantom {

  type UnsafeExt[+R <: Record, +S <: Record] <: this.Any

  type Ext[+R <: Record, S <: Record] <: UnsafeExt[R, S]

  def materializeExt[R <: Record, S <: Record]: Ext[R, S] = assume

}