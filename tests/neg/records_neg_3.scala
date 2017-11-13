import dotty.records._

import scala.collection.immutable.HashMap

class MyRecord(val _data: HashMap[String, Any]) extends Record (_data) {
  val foo = "foo"
}

object records_neg_3 {
  val r = new MyRecord(HashMap())
  val s = Record(foo = "bar")
  val t1 = r.extend(s) // error: Cannot prove that the record types are disjoint.
  val t2 = s.extend(r) // error: Cannot prove that the record types are disjoint.
}