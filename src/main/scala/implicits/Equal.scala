package implicits

trait Equal[T] {
  def apply(a:T, b:T): Boolean
}
