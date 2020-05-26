package implicits

case class Person(name: String, age: Int) {
}

object Person {
  implicit def ordering: Ordering[Person] = Ordering.by(_.name)
}
