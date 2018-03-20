import dotty.records.Extensible

class Rec extends Selectable {
  override def selectDynamic(name: String) = 42
  val myValDef = 1
  private val myPrivValDef = 2
  def myDefDef0 = 3
  def myDefDef1() = 4
}

object ExtensibleTest {
  // extension cannot overwrite class member
  implicitly[Extensible[Rec, "myValDef", Int]] // error

  // doesn't matter if it's private
  implicitly[Extensible[Rec, "myPrivValDef", Int]] // error

  // or a method
  implicitly[Extensible[Rec, "myDefDef0", Int]] // error
  implicitly[Extensible[Rec, "myDefDef1", Int]] // error
}