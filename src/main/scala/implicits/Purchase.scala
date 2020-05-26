package implicits

case class Purchase(numberOfUnits: Int, unitPrice: Double) {
  def totalPrice: Double = numberOfUnits * unitPrice
}

object Purchase {
  implicit def ordering: Ordering[Purchase] = Ordering.by(-_.totalPrice)
}

object OrderByNumberOfUnits {
  implicit def ordering: Ordering[Purchase] = Ordering.by(_.numberOfUnits)
}

object OrderByUnitPrice {
  implicit def ordering: Ordering[Purchase] = Ordering.by(_.unitPrice)
}