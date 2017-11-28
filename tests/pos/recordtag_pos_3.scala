import dotty.records._

object recordtag_pos_3 {

  def main(args: Array[String]): Unit = {

    val r = Record(
      name="Olle",
      age=29,
      phones=List(
        Record(
          nr="+46 70 12345678",
          to="mobile"
        ),
        Record(
          nr="+46 8 123456",
          to="home"
        )
      ): List[Record{val nr: String; val to: String}] // FIXME: Why is this needed?
    )

    println(r.phones(1).nr)

    r.safeCast[Record{val phones: List[Record{val to: String}]}] match {
      case Some(r) => println(s"This is for sure a person we call at ${r.phones.map(p => p.to).mkString(""," and ", "")}")
      case None => println("Can't call this person")
    }

    r.safeCast[Record{val phones: List[Record{val nr: Int}]}] match {
      case Some(r) => println("NoooooooooooooooOO!!!")
      case None => println("SAFE!")
    }
  }
}