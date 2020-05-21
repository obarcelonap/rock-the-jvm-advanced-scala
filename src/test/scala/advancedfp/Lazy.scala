package advancedfp

class Lazy[A](value: => A) {
  lazy val get: A = value

  def flatMap[B](f: A => Lazy[B]): Lazy[B] = f(value)
}

object Lazy {
  def apply[A](value: => A): Lazy[A] = new Lazy(value)
}
