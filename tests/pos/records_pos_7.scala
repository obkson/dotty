import dotty.records._

object records_pos_7 {

  class A
  class B extends A
  class C extends B

  /*
  def addF[R <: Record](r: R)(implicit ev: Ext[R, Record{val f: C}]): R & Record{val f: A} = {
    val s = Record(f=new A())
    val t = r ++ s // no implicit found
    t
  }
  */

  def updateF[R <: Record](r: R)(implicit ev: Ext[R, Record{val f: B}]): R & Record{val f: B} = {
    val t = r ++ Record(f=new B()) // no implicit found
    t
  }

  def main(args: Array[String]): Unit = {
    // create
    val r = Record(f=new A())

    // update using polymorphic function
    val s = updateF(r) // amazingly, we manage to cast the field `f` from type `A` to `B` in a sound way
    val f: B = s.f
    println(f) // B()
  }
}