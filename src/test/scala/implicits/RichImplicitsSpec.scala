package implicits

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import RichImplicits._

import scala.util.Failure

class RichImplicitsSpec extends AnyFunSpec with Matchers {

  describe("RichString") {
    describe("asInt") {
      it("should return a success integer when is numeric") {
        val string = "42"

        val stringAsInt = string.asInt

        stringAsInt.get should be(42)
      }
      it("should return a failure when is not numeric") {
        val string = "bang"

        val intFailure = string.asInt

        intFailure.isInstanceOf[Failure[Int]] should be (true)
      }
    }

    describe("encrypt") {
      it("should shift two positions in the alphabet") {
        val text = "john"

        val encryptedText = text.encrypt(2)

        encryptedText should be ("lqjp")
      }
    }
  }

  describe("RichInt") {
    describe("times") {
      it("should execute the callback the value times") {
        var num = 0
        val callback = () => num = num + 1

        3.times(callback)

        num should be (3)
      }
    }
    describe("concatenator") {
      it("should concat the same list the value times") {
        val list = List(1, 2)

        val newList = 3 * list

        newList should be (List(1, 2, 1, 2, 1, 2))
      }
    }
  }
}
