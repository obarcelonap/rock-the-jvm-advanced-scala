package advancedfp

import scala.annotation.tailrec

abstract class MyStream[+A] {

  def isEmpty: Boolean
  def head: A
  def tail: MyStream[A]

  def #::[B >: A](elem: B): MyStream[B]
  def ++[B >: A](otherStream: => MyStream[B]): MyStream[B]

  def foreach(f: A => Unit): Unit
  def map[B](f: A => B): MyStream[B]
  def flatMap[B](f: A => MyStream[B]): MyStream[B]
  def filter(p: A => Boolean): MyStream[A]

  def take(n: Int): MyStream[A]
  def takeAsList(n: Int): List[A]
}

class MyEmptyStream extends MyStream[Nothing] {
  override def isEmpty: Boolean = true

  override def head: Nothing = throw new NoSuchElementException
  override def tail: MyStream[Nothing] = throw new NoSuchElementException

  override def #::[B >: Nothing](elem: B): MyStream[B] = new MyStreamValue(elem, this)
  override def ++[B >: Nothing](otherStream: => MyStream[B]): MyStream[B] = otherStream
  override def foreach(f: Nothing => Unit): Unit = ()

  override def map[B](f: Nothing => B): MyStream[B] = new MyEmptyStream
  override def flatMap[B](f: Nothing => MyStream[B]): MyStream[B] = new MyEmptyStream
  override def filter(p: Nothing => Boolean): MyStream[Nothing] = this

  override def take(n: Int): MyStream[Nothing] = this
  override def takeAsList(n: Int): List[Nothing] = List()
}

class MyStreamValue[+A](current: A, next: => MyStream[A]) extends MyStream[A] {
  override def isEmpty: Boolean = false

  override val head: A = current
  override lazy val tail: MyStream[A] = next

  override def #::[B >: A](elem: B): MyStream[B] = new MyStreamValue[B](elem, this)
  override def ++[B >: A](otherStream: => MyStream[B]): MyStream[B] = new MyStreamValue[B](head, tail ++ otherStream)
  override def foreach(f: A => Unit): Unit = {
    f(head)
    tail.foreach(f)
  }

  override def map[B](f: A => B): MyStream[B] = new MyStreamValue[B](f(head), tail.map(f))
  override def flatMap[B](f: A => MyStream[B]): MyStream[B] = {
    println(s"head $head")
    f(head) ++ tail.flatMap(f)
  }
  override def filter(p: A => Boolean): MyStream[A] = {
    lazy val newTail = tail.filter(p)
    if (p(head)) new MyStreamValue[A](head, newTail)
    else newTail
  }
  override def take(n: Int): MyStream[A] = {
    if (n <= 0) new MyEmptyStream
    else if (n == 1) new MyStreamValue[A](head, new MyEmptyStream)
    else new MyStreamValue(head, tail.take(n - 1))
  }
  override def takeAsList(n: Int): List[A] = {
    if (n <= 0) List()
    else head +: tail.takeAsList(n - 1)
  }
}

object MyStream {
  def from[A](start: A)(generator: A => A): MyStream[A] =
    new MyStreamValue[A](start, from(generator(start))(generator))
  def of[A](values: A*): MyStream[A] = {
    @tailrec
    def ofRec(seq: Seq[A], stream: MyStream[A] = new MyEmptyStream): MyStream[A] =
      if (seq.isEmpty) stream
      else ofRec(seq.tail, new MyStreamValue[A](seq.head, stream))

    ofRec(values.toSeq.reverse)
  }
}
