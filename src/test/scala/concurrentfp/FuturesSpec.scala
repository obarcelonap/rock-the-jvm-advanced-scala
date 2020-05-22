package concurrentfp

import java.util.concurrent.atomic.AtomicInteger

import org.scalatest.funspec.AsyncFunSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future

class FuturesSpec extends AsyncFunSpec with Matchers {

  describe("immediate") {
    it("should return immediately the value") {
      val future = Futures.immediate("foo")

      future map {
        value => value should be ("foo")
      }
    }
  }
  describe("sequence") {
    it("should execute futures in sequence") {
      var result = ""
      val concatToResult = (value: String) => Future {
        result.synchronized {
          result += value
          result
        }
      }
      val futuresSequence = Futures.sequence(
        concatToResult("first"),
        concatToResult("second"),
      )

      futuresSequence map {
        result => result should be ("first", "firstsecond")
      }
    }
  }
  describe("first") {
    it("should return first when first arg is the first finishing result") {
      val first = Futures.first(Future("first"), Futures.delay(50)("second"))

      first map {
        value => value should be ("first")
      }
    }
    it("should return second when second arg is the first finishing result") {
      val first = Futures.first(Futures.delay(50)("first"), Future("second"))

      first map {
        value => value should be ("second")
      }
    }
  }
  describe("last") {
    it("should return first when first arg is the last finishing result") {
      val last = Futures.last(Futures.delay(50)("first"), Future("second"))

      last map {
        value => value should be ("first")
      }
    }
    it("should return second when second arg is the last finishing result") {
      val last = Futures.last(Future("first"), Futures.delay(50)("second"))

      last map {
        value => value should be ("second")
      }
    }
  }
  describe("retryUntil") {
    it("should execute same future until a condition is met") {
      val counter  = new AtomicInteger

      val result = Futures.retryUntil(() => Future {
        counter.incrementAndGet()
      }, (counter: Int) => {
        counter >= 10
      })

      result map {
        value => value should be (10)
      }
    }
  }
}
