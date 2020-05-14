package advancedfp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class AsciiSpec extends AnyFunSpec with Matchers {

  describe("toCharacter") {
    val testCases = List(
      (65, "A"),
      (66, "B"),
      (67, "C"),
      (68, "D"),
      (69, "E"),
    )
    for ((number, character) <- testCases) {
      it(s"should convert $number as '$character'") {
        val actual = Ascii.toCharacter(number)

        actual should be(character)
      }
    }
    it("should throw exception for any other number") {
      intercept[NoSuchElementException] {
        Ascii.toCharacter(0)
      }
    }
  }
}
