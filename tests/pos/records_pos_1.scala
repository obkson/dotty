import dotty.records._

object records_pos_1 {

  def equal[R <: Record](r: R, s: R) = r == s

  def main(args: Array[String]): Unit = {

    // equality by value
    assert(Record(a="a") == Record(a="a"))
    // even under the same static type
    assert(!equal(Record(a="a"), Record(a="a", b="b")))

  }

}