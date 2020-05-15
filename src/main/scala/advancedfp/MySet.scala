package advancedfp

import scala.annotation.tailrec

trait MySet[A] extends (A => Boolean) {

  def head: A
  def tail: MySet[A]

  override def apply(v1: A): Boolean = contains(v1)

  def isEmpty: Boolean
  def contains(elem: A): Boolean
  def +(elem: A): MySet[A]
  def ++(otherSet: MySet[A]): MySet[A]
  def map[B](f: A => B): MySet[B]
  def flatMap[B](f: A => MySet[B]): MySet[B]
  def filter(f: A => Boolean): MySet[A]

  override def toString(): String = {
    @tailrec
    def stringify(set: MySet[A], accum: String = ""): String =
      if (set.isEmpty) accum
      else if (accum.isEmpty) stringify(set.tail, set.head.toString)
      else stringify(set.tail, s"$accum, ${set.head}")

    if (isEmpty) "{}"
    else s"{${stringify(this)}}"
  }
}

object MySet {

  def apply[A](values: A*): MySet[A] = {
    @tailrec
    def build(list: List[A], set: MySet[A]): MySet[A] =
      if (list.isEmpty) set
      else build(list.tail, set + list.head)

    build(values.toList, new MyEmptySet)
  }
}

class MyEmptySet[A] extends MySet[A] {
  override def head: A = throw new NoSuchElementException
  override def tail: MySet[A] = throw new NoSuchElementException

  override def isEmpty: Boolean = true
  override def contains(elem: A): Boolean = false
  override def +(elem: A): MySet[A] = MySetValue(elem, new MyEmptySet)
  override def ++(otherSet: MySet[A]): MySet[A] = otherSet
  override def map[B](f: A => B): MySet[B] = new MyEmptySet[B]
  override def flatMap[B](f: A => MySet[B]): MySet[B] = new MyEmptySet[B]
  override def filter(f: A => Boolean): MySet[A] = this

  override def equals(obj: Any): Boolean = obj.isInstanceOf[MyEmptySet[A]]
}

case class MySetValue[A](head: A, tail: MySet[A]) extends MySet[A]{
  override def isEmpty = false
  override def contains(elem: A): Boolean = head.equals(elem) || tail.contains(elem)
  override def +(elem: A): MySet[A] =
    if (contains(elem)) this
    else MySetValue(elem, this)
  override def ++(otherSet: MySet[A]): MySet[A] = otherSet ++ tail + head
  override def map[B](f: A => B): MySet[B] = tail.map(f) + f(head)
  override def flatMap[B](f: A => MySet[B]): MySet[B] = f(head) ++ tail.flatMap(f)
  override def filter(f: A => Boolean): MySet[A] = {
    val filteredTail = tail.filter(f)
    if (f(head)) filteredTail + head
    else filteredTail
  }

  override def equals(obj: Any): Boolean = obj match {
    case otherSet: MySetValue[A] => {
      @tailrec
      def containsAll(set: MySet[A], values: MySet[A]): Boolean =
        if (values.isEmpty) true
        else if (!set.contains(values.head)) false
        else containsAll(set, values.tail)

      containsAll(this, otherSet) && containsAll(otherSet, this)
    }
    case _ => false
  }

}



