package advancedfp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class LazySpec extends AnyFunSpec with Matchers {

  describe("behaviour") {
    it ("should evaluate the value only when value is retrieven") {
      val lazyValue = Lazy(throw new RuntimeException)

      intercept[RuntimeException] {
        lazyValue.get
      }
    }
    it ("should flatMap the given value") {
      val lazyValue = Lazy("monad")

      val newLazyValue = lazyValue.flatMap(value => Lazy(s"my $value"))

      newLazyValue.get should be ("my monad")
    }
    it ("should map the given value") {
      val lazyValue = Lazy("monad")

      val newLazyValue = lazyValue.map(value => s"my $value")

      newLazyValue.get should be ("my monad")
    }
    it ("should flatten when the value is lazy too") {
      val lazyValue = Lazy(Lazy("monad"))

      val newLazyValue = lazyValue.flatten

      newLazyValue.get should be ("monad")
    }
    it ("should return itself when flattening and value is not lazy") {
      val lazyValue = Lazy("monad")

      val newLazyValue = lazyValue.flatten

      newLazyValue.get should be ("monad")
    }
  }

  describe("monad laws") {
    val x = "monad"
    val monad = Lazy(x)
    val f: (=> String) => Lazy[String] = value => Lazy(s"hello $value")
    val g: (=> String) => Lazy[String] = value => Lazy(s"$value!")

    it("should satisfy left-identity law") {
      assert(monad.flatMap(f).get == f(x).get)
    }
    it("should satisfy right-identity law") {
      assert(monad.flatMap(Lazy(_)).get == monad.get)
    }
    it("should satisfy associativity law") {
      assert(monad.flatMap(f).flatMap(g).get == monad.flatMap(x => f(x).flatMap(g)).get)
    }
  }
}
