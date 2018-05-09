import dotty.records._

object FieldTest {

  implicitly[FieldTyper["a", Int]]
  
  val a: Field["a", Int] = Field("a", 1)

  val b = Field("b", "two")
  implicitly[b.type <:< Field["b", String]]

  val c: Field["c", Int] = "c" ->> 3

  val d = "d"->>"four"
  implicitly[d.type <:< Field["d", String]]

}