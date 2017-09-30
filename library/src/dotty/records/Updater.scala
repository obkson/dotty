package dotty
package object records {

  // Currently, we cannot do this since all dotty-code is compiled with Scala 2.12 :( :( :(
  // type Updater[L <: String, V] = [R <: Record] => PolymorphicUpdater[R, L, V]

  type Updater[R <: Record, L <: String, V] = PolymorphicUpdater[R, L, V]
}