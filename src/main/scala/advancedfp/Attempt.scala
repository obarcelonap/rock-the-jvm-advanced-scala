package advancedfp

abstract class Attempt[+A] {
  def flatMap[B](f: A => Attempt[B]): Attempt[B]
}

object Attempt {
  def apply[A](value: => A): Attempt[A] =
    try {
      SuccessfulAttempt(value)
    } catch {
      case e: Throwable => FailedAttempt(e)
    }
}

case class FailedAttempt(e: Throwable) extends Attempt[Nothing] {
  override def flatMap[B](f: Nothing => Attempt[B]): Attempt[B] = this
}

case class SuccessfulAttempt[A](value: A) extends Attempt[A] {
  override def flatMap[B](f: A => Attempt[B]): Attempt[B] =
    try {
      f(value)
    } catch {
      case e: Throwable => FailedAttempt(e)
    }
}

