import dotty.records._

object FieldTest {

  implicitly[FieldTyper["a", Int]]
  
  val a: Field["a", Int]{ type Out = Selectable{val a: Int} } = Field("a", 1)

  val b = Field("b", "two")

  val c: Field["c", Int]{ type Out = Selectable{val c: Int} } = "c" ->> 3

  val d = "d"->>"four"

}