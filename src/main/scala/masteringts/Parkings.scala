package masteringts

object Parkings {

  class InvariantParking[A](things: List[A] = List()) {
    def park(vehicle: A): InvariantParking[A] = this
    def impound(vehicles: List[A]): InvariantParking[A] = this
    def check(condition: String): List[A] = List()
    def flatMap[B](f: A => B): InvariantParking[B] = new InvariantParking[B]()
  }

  class CovariantParking[+A](things: List[A] = List()) {
    def park[B >: A](vehicle: B): CovariantParking[B] = new CovariantParking[B]
    def impound[B >: A](vehicles: List[B]): CovariantParking[B] = new CovariantParking[B]
    def check(condition: String): List[A] = List()
    def flatMap[B](f: A => B): CovariantParking[B] = new CovariantParking[B]()
  }

  class ContravariantParking[-A](things: List[A] = List()) {
    def park(vehicle: A): ContravariantParking[A] = new ContravariantParking[A]
    def impound(vehicles: List[A]): ContravariantParking[A] = new ContravariantParking[A]
    def check[B <: A](condition: String): List[B] = List()
    def flatMap[B <: A, C](f: B => C): ContravariantParking[C] = new ContravariantParking[C]()
  }

  class Vehicle
  class Bike extends Vehicle
  class Car extends Vehicle
}
