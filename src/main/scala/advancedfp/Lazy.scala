package advancedfp

class Lazy[+A](value: => A) {
  lazy val get: A = value

  def flatMap[B](f: (=> A) => Lazy[B]): Lazy[B] = f(value)
  def map[B](f: A => B): Lazy[B] = Lazy(f(value))
  def flatten: Lazy[A] = value match {
    case v: Lazy[A] => v
    case _ => this
  }
}

object Lazy {
  def apply[A](value: => A): Lazy[A] = new Lazy(value)
}
