import dotty.records._

import scala.collection.immutable.HashMap

object record_json {

  def main(args: Array[String]): Unit = {

    val map = HashMap[String,Any]("name"->"Olle", "age"->42)

    val r = new Record(map).safeCast[Record{val name: String; val age: Int}] match {
      case Some(s) => println(s)
      case None => println("invalid json")
    }

    new Record(map) match {
      case Record(s){val foo: Int} => println("hm....")
      case Record(s){val name: String; val age: Int} => println("hehehehe!")
      case _ => println("wtf?")
    }
  }
}