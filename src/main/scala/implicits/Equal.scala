package implicits

trait Equal[T] {
  def apply(a:T, b:T): Boolean
}

object Equal {
  def apply[T](a:T, b:T)(implicit equal: Equal[T]): Boolean = equal.apply(a, b)
}