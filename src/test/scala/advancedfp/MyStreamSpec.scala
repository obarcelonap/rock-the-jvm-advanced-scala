package advancedfp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class MyStreamSpec extends AnyFunSpec with Matchers {

  val naturalsStream = MyStream.from(0)(_ + 1)
  describe("object") {

    it("creates a stream of naturals") {
      val naturalsUntil10 = naturalsStream.takeAsList(10)

      naturalsUntil10 should be (List(0, 1, 2, 3, 4, 5, 6, 7, 8, 9))
    }
    it("filters out first five even numbers") {
      val firstFiveEvenNumbers = naturalsStream
          .filter(_ %2 == 0)
          .takeAsList(5)

      firstFiveEvenNumbers should be (List(0, 2, 4, 6, 8))
    }
    it("maps to string first five numbers") {
      val firstFiveOddNumbersAsString = naturalsStream
        .map(String.valueOf)
        .takeAsList(5)

      firstFiveOddNumbersAsString should be (List("0", "1", "2", "3", "4"))
    }
    it("flat maps to number and number + 1 first five numbers") {
      val firstFiveNumbersPlusOne = naturalsStream
        .flatMap(value => MyStream.of(value, value + 1))
        .takeAsList(10)
      firstFiveNumbersPlusOne should be (List(0, 1, 1, 2, 2, 3, 3, 4, 4, 5))
    }
  }
  describe("fibonacci") {
    it("should return first 8 numbers of fibonacci sequence") {
      val fibonacci = MyStream.from((0, 1))(tup => (tup._2, tup._1 + tup._2))
          .map(_._1)
          .takeAsList(8)

      fibonacci should be (List(0, 1, 1, 2, 3, 5, 8, 13))
    }
  }

  describe("Erathostenes sieve") {
    it("should return first 8 prime numbers") {
      val first8Primes = MyStream.from(MyStream.from(2)(_ + 1))(stream => stream.tail.filter(_ % stream.head != 0))
        .map(_.head)
        .takeAsList(8)

      first8Primes should be (List(2, 3, 5, 7, 11, 13, 17, 19))
    }
  }
}
