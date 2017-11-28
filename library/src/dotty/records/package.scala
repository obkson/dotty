package dotty
package object records {
  type Ext[+R <: Record, S <: Record] = RecordExtensions.Ext[R, S]
  type Extender[S <: Record] = [+R <: Record] => RecordExtensions.Ext[R, S]
}