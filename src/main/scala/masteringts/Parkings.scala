package masteringts

object Parkings {

  class InvariantParking[A](things: List[A] = List()) {
    def park(vehicle: A): InvariantParking[A] = this
    def impound(vehicles: List[A]): InvariantParking[A] = this
    def check(condition: String): List[A] = List()
  }

  class CovariantParking[+A](things: List[A] = List()) {
    def park[B >: A](vehicle: B): CovariantParking[B] = new CovariantParking[B]
    def impound[B >: A](vehicles: List[B]): CovariantParking[B] = new CovariantParking[B]
    def check(condition: String): List[A] = List()
  }

  class ContravariantParking[-A](things: List[A] = List()) {
    def park(vehicle: A): ContravariantParking[A] = new ContravariantParking[A]
    def impound(vehicles: List[A]): ContravariantParking[A] = new ContravariantParking[A]
    def check[B <: A](condition: String): List[B] = List()
  }

  class Vehicle
  class Bike extends Vehicle
  class Car extends Vehicle
}
