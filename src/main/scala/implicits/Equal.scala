package implicits

trait Equal[T] {
  def apply(a:T, b:T): Boolean
}

object Equal {
  def apply[T](a:T, b:T)(implicit equal: Equal[T]): Boolean = equal.apply(a, b)
}

object EqualImplicits {
  implicit class RichEqual[T](value: T) {
    def ===(other: T)(implicit equal: Equal[T]): Boolean = equal.apply(value, other)
    def !==(other: T)(implicit equal: Equal[T]): Boolean = !equal.apply(value, other)
  }
}