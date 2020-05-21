package concurrentfp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class InceptionThreadsSpec extends AnyFunSpec with Matchers {

  describe("apply") {
    it("should output 10 times in reverse order") {
      val times = 10
      var output = ""

      val thread = InceptionThread(times)(value => output.synchronized {
        if (output.isEmpty) output = value
        else output += s", $value"
      })

      thread.start()
      thread.join()

      output should be ("1, 2, 3, 4, 5, 6, 7, 8, 9, 10")
    }
  }

}
