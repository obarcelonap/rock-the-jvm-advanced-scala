package advancedfp

import org.scalatest.Inside
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class AttemptSpec extends AnyFunSpec with Matchers with Inside {

  describe("behaviour") {
    it("should catch exceptions") {
      val failure = Attempt(throw new IllegalArgumentException)

      inside(failure) {
        case FailedAttempt(e) =>
          e.isInstanceOf[IllegalArgumentException] should be (true)
      }
    }
    it("should flatMap when there is value") {
      val success = Attempt("monads")
        .flatMap((value: String) => Attempt(s"$value, $value"))

      inside(success) {
        case SuccessfulAttempt(value) =>
          value should be ("monads, monads")
      }
    }
    it("should skip flatMap when already failed") {
      val failure = Attempt(throw new IllegalArgumentException)
        .flatMap((value: String) => Attempt("doesnotmatter"))

      inside(failure) {
        case FailedAttempt(e) =>
          e.isInstanceOf[IllegalArgumentException] should be (true)
      }
    }
  }
  describe("monad laws") {
    val x = "monad"
    val monad = Attempt(x)
    val f = (value: String) => Attempt(s"hello $value")
    val g = (value: String) => Attempt(s"$value!")

    it("should satisfy left-identity law") {
      assert(Attempt(x).flatMap(f) == f(x))
    }
    it("should satisfy right-identity law") {
      assert(monad.flatMap(Attempt(_)) == monad)
    }
    it("should satisfy associativity law") {
      assert(monad.flatMap(f).flatMap(g) == monad.flatMap(x => f(x).flatMap(g)))
    }
  }
}

