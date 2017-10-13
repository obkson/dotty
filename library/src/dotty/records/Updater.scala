package dotty
package object records {

  type Updater[L <: String, V] = [R <: Record] => PolymorphicUpdater[R, L, V]

}