import dotty.records._

object RecordTest {
  val r = Record + ("a", 1) + ("b", "two")
  val ra = r.a
  val rb = r.b

  val s = Record + ("a"->>1) + ("b"->>"two")
  val sa = s.a
  val sb = s.b

  val t = Record + ("a"->>1) + ("b", "two") + ("c"->>3) + ("d", "four")
  val ta = t.a
  val tb = t.b
  val tc = t.c
  val td = t.d
}