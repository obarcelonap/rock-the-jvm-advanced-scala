package implicits

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class UserSpec extends AnyFunSpec with Matchers {

  describe("EqualByName") {
    it("should be equal when two users has same name") {
      val john = User("john", "john@bla.com")
      val johny = User("john", "johny@bla.com")

      EqualByName(john, johny) should be (true)
    }
    it("should be different when two users has different name") {
      val john = User("john", "john@bla.com")
      val johny = User("johny", "johny@bla.com")

      EqualByName(john, johny) should be (false)
    }
  }

  describe("CompletelyEqual") {
    it("should be equal when two users are the same") {
      val john = User("john", "john@bla.com")

      CompletelyEqual(john, john) should be (true)
    }
    it("should be different when two users has different name") {
      val john = User("john", "john@bla.com")
      val johny = User("johny", "john@bla.com")

      CompletelyEqual(john, johny) should be (false)
    }
    it("should be different when two users has different email") {
      val john = User("john", "john@bla.com")
      val johny = User("john", "johny@bla.com")

      CompletelyEqual(john, johny) should be (false)
    }
  }
}
