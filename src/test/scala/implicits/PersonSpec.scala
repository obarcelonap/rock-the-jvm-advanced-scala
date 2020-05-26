package implicits

import implicits.Person.ordering
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers


class PersonSpec extends AnyFunSpec with Matchers {

  val steve = Person("Steve", 30)
  val amy = Person("Amy", 22)
  val john = Person("John", 66)

  describe("sorting") {
    it("should order ascendant by name") {
      val persons = List(steve, amy, john)

      val personsByNameAsc = persons.sorted

      personsByNameAsc should be(List(amy, john, steve))
    }
  }
}
