import dotty.records._

object records_pos_8 {

  def main(args: Array[String]): Unit = {

    val r = Record.fromJSON("""{"foo": 42}""")
    println(r)
  }
}

// Any Map[String -> Any] can be casted to a record, or throw runtime error

// val r = new Record(map).safeCast[Record{val foo: Int}]


// JSONFormatter[Record] = new JSONFormatter[Record] {
//
// }


implicit object PersonFormat extends JsonFormat[Person] {

  def write[J](x: Person, builder: Builder[J]): Unit = {
    builder.beginObject()
    builder.addField("name", x.name)
    builder.addField("value", x.value)
    builder.endObject()
  }

  def read[J](jsOpt: Option[J], unbuilder: Unbuilder[J]): Person =
    jsOpt match {
      case Some(js) =>
        unbuilder.beginObject(js)
        val name = unbuilder.readField[String]("name")
        val value = unbuilder.readField[Int]("value")
        unbuilder.endObject()
        Person(name, value)
      case None =>
        deserializationError("Expected JsObject but found None")
    }
}