package advancedfp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class MySetSpec extends AnyFunSpec with Matchers {

  describe("contains") {
    it("should return false when empty set") {
      val mySet = MySet[String]()

      val contains = mySet.contains("1")

      contains should be(false)
    }
    it("should return false when set does not contain the value") {
      val mySet = MySet("a")

      val contains = mySet.contains("1")

      contains should be(false)
    }
    it("should return true when set contain the value") {
      val mySet = MySet("a")

      val contains = mySet.contains("a")

      contains should be(true)
    }
  }
  describe("+") {
    it("should append an elem to an empty set") {
      val mySet = MySet[String]()

      val newSet = mySet + "a"

      newSet should be(MySet("a"))
    }
    it("should append an elem to a set with values") {
      val mySet = MySet("a")

      val newSet = mySet + "b"

      newSet should be(MySet("a", "b"))
    }
    it("should return same set when elem is already in the set") {
      val mySet = MySet("a")

      val newSet = mySet + "a"

      newSet should be(mySet)
    }
  }
  describe("++") {
    it("should append set to an empty set") {
      val mySet = MySet("a")

      val newSet = MySet() ++ mySet

      newSet should be(mySet)
    }
    it("should append a set with values to a set with values") {
      val mySet1 = MySet("a")
      val mySet2 = MySet("b")

      val newSet = mySet1 ++ mySet2

      newSet should be(MySet("a", "b"))
    }
    it("should not duplicate existing values in both sets") {
      val mySet1 = MySet("a", "c")
      val mySet2 = MySet("c", "b")

      val newSet = mySet1 ++ mySet2

      newSet should be(MySet("a", "b", "c"))
    }
  }
  describe("map") {
    it("should do nothing to an empty set") {
      val mySet = MySet[String]()

      val newSet = mySet.map(value => value + "!")

      newSet should be(mySet)
    }
    it("should map the values of a set") {
      val mySet = MySet("a", "b")

      val newSet = mySet.map(value => value + "!")

      newSet should be(MySet("a!", "b!"))
    }
  }
  describe("flatMap") {
    it("should do nothing to an empty set") {
      val mySet = MySet[String]()

      val newSet = mySet.flatMap(_ => MySet(1, 2))

      newSet should be(MySet[Int]())
    }
    it("should map the values of a set") {
      val mySet = MySet("a", "b")

      val newSet = mySet.flatMap(value => MySet(value, value + "!"))

      newSet should be(MySet("a", "a!", "b", "b!"))
    }
  }
  describe("filter") {
    it("should do nothing to an empty set") {
      val mySet = MySet[String]()

      val newSet = mySet.filter(value => value.equals("a"))

      newSet should be(mySet)
    }
    it("should map the values of a set") {
      val mySet = MySet("a", "b")

      val newSet = mySet.filter(value => value.equals("a"))

      newSet should be(MySet("a"))
    }
  }
  describe("remove") {
    it("should do nothing to an empty set") {
      val mySet = MySet[String]()

      val newSet = mySet - "a"

      newSet should be(mySet)
    }
    it("should remove one value from a set") {
      val mySet = MySet("a", "b")

      val newSet = mySet - "a"

      newSet should be(MySet("b"))
    }
  }
  describe("&") {
    it("should return empty with two empty set") {
      val mySet1 = MySet[String]()
      val mySet2 = MySet[String]()

      val newSet = mySet1 & mySet2

      newSet should be(MySet[String]())
    }
    it("should return empty with one empty set and other with values") {
      val mySet1 = MySet("a", "b")
      val mySet2 = MySet[String]()

      val newSet = mySet1 & mySet2

      newSet should be(MySet[String]())
    }
    it("should return values in both sets when both has values") {
      val mySet1 = MySet("a", "b")
      val mySet2 = MySet("b", "c")

      val newSet = mySet1 & mySet2

      newSet should be(MySet("b"))
    }
  }
  describe("--") {
    it("should return empty with two empty set") {
      val mySet1 = MySet[String]()
      val mySet2 = MySet[String]()

      val newSet = mySet1 -- mySet2

      newSet should be(MySet[String]())
    }
    it("should return the set with values with one empty set and other with values") {
      val mySet1 = MySet("a", "b")
      val mySet2 = MySet[String]()

      val newSet = mySet1 -- mySet2

      newSet should be(mySet1)
    }
    it("should return values not present in both sets when both has values") {
      val mySet1 = MySet("a", "b")
      val mySet2 = MySet("b", "c")

      val newSet = mySet1 -- mySet2

      newSet should be(MySet("a", "c"))
    }
  }
  describe("!") {
    it("returns a set containing all the possible values when negating empty set") {
      val mySet = MySet[String]()

      val newSet = !mySet

      newSet("doesnotmatter") should be(true)
    }

    it("returns a set containing infinite values except the originals when negating a set with values") {
      val mySet = MySet[String]("a", "b", "c")

      val newSet = !mySet

      newSet("a") should be(false)
      newSet("b") should be(false)
      newSet("c") should be(false)
      newSet("d") should be(true)
      newSet("e") should be(true)
    }
  }
}
