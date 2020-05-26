package implicits

case class User(name: String, email: String) {
  implicit object EqualByName extends Equal[User] {
    override def apply(a: User, b: User): Boolean = a.name == b.name
  }
}

object implicits {
  implicit object EqualByName extends Equal[User] {
    override def apply(a: User, b: User): Boolean = a.name == b.name
  }
}

object CompletelyEqual extends Equal[User] {
  override def apply(a: User, b: User): Boolean = a.name == b.name && a.email == b.email
}