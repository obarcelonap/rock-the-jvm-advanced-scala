package concurrentfp

import scala.annotation.tailrec
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.util.Try


object Futures {
  def immediate[A](value: A): Future[A] = Future(value)

  def delay[A](millis: Int)(value: A): Future[A] = Future {
    Thread.sleep(millis)
    value
  }

  def sequence[A, B](first: Future[A], next: Future[B]): Future[(A, B)] = for {
    firstResult <- first
    nextResult <- next
  } yield (firstResult, nextResult)

  def first[A](future: Future[A], other: Future[A]): Future[A] = {
    val promise = Promise[A]
    future.onComplete(promise.tryComplete)
    other.onComplete(promise.tryComplete)

    promise.future
  }

  def last[A](future: Future[A], other: Future[A]): Future[A] = {
    val firstPromise = Promise[A]
    val lastPromise = Promise[A]

    val completePromises = (res: Try[A]) =>
      if (!firstPromise.tryComplete(res))
        lastPromise.complete(res)

    future.onComplete(completePromises)
    other.onComplete(completePromises)

    lastPromise.future
  }

  def retryUntil[A](supplier: () => Future[A], predicate: A => Boolean): Future[A] = supplier()
    .filter(predicate)
    .recoverWith {
      case _ => retryUntil(supplier, predicate)
    }
}
