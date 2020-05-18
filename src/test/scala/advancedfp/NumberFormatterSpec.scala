package advancedfp

import advancedfp.NumberFormatter.{formatWithSixDecimals, formatWithTwelveDecimals, formatWithTwoDecimals, expandWithDifferentFormats}
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class NumberFormatterSpec extends AnyFunSpec with Matchers {

  describe("NumberFormatter") {
    it("should format with two decimals") {
      val number = Math.PI

      val formatted =  formatWithTwoDecimals(number)

      formatted should be ("3.14")
    }
    it("should format with six decimals") {
      val number = Math.PI

      val formatted = formatWithSixDecimals(number)

      formatted should be ("3.141593")
    }
    it("should format with twelve decimals") {
      val number = Math.PI

      val formatted = formatWithTwelveDecimals(number)

      formatted should be ("3.141592653590")
    }
    it("should format every number using three different formatters") {
      val numbers: List[Number] = List(Math.PI)

      val formatted = expandWithDifferentFormats(numbers)

      formatted should be (List(("3.14", "3.141593", "3.141592653590")))
    }
  }
}
