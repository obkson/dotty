import dotty.records._

object recordtag_pos_2 {

  def main(args: Array[String]): Unit = {

    val r = Record(
      name="Olle",
      age=29,
      adress=Record(
        street="Ulvsundav. 12",
        zip="167 33",
        city="Brooma",
        country="Sweden"
      )
    )
    println(r.adress.street)
    // println(r.adress.street: Int) // correctly fails at compile time

    r.safeCast[Record{val name: String; val age: Int}] match {
      case Some(r) => println(s"Success! ${r.name}")
      case None => println("fail")
    }

    r.safeCast[Record{val adress: Record{val zip: Int}}] match {
      case Some(s) => {
        val zip: Int = s.adress.zip
        println(zip)
      }
      case None => println("fail")
    }
  }
}