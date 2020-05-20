package advancedfp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class MyStreamSpec extends AnyFunSpec with Matchers {

  describe("object") {
    val naturalsStream = MyStream.from(0)(_ + 1)

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
      val firstFiveOddNumbersAsString = naturalsStream
        .flatMap(value => MyStream.of(value, value + 1))
        .takeAsList(10)
      firstFiveOddNumbersAsString should be (List(0, 1, 1, 2, 2, 3, 3, 4, 4, 5))
    }
  }
}
