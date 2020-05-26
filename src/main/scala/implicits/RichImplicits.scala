package implicits

import scala.util.Try

object RichImplicits {
  implicit class RichString(val value: String) extends AnyVal {
    def asInt: Try[Int] = Try(Integer.parseInt(value))
    def encrypt(distance: Int): String = value.map(c => (c.toInt + distance).toChar)
  }

  implicit class RichInt(val value: Int) extends AnyVal {
    def times(callback: () => Unit): Unit = (1 to value).foreach(_ => callback())
    def *[A](list: List[A]): List[A] = (1 to value).flatMap(_ => list).toList
  }
}
