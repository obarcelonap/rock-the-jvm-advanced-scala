package masteringts

import masteringts.Parkings._
import org.scalatest.funspec.AnyFunSpec

class ParkingsSpec extends AnyFunSpec {

  describe("Invariant parking") {
    describe("construction") {
      describe("can be assigned to same type") {
        it("vehicles") {
          val parking: InvariantParking[Vehicle] = new InvariantParking[Vehicle]()
        }
        it("bikes") {
          val parking: InvariantParking[Bike] = new InvariantParking[Bike]()
        }
        it("cars") {
          val parking: InvariantParking[Car] = new InvariantParking[Car]()
        }
      }
      it("can't be assigned to sub types") {
        //        val parking: InvariantParking[Bike] = new InvariantParking[Vehicle]()
        //        val parking: InvariantParking[Car] = new InvariantParking[Vehicle]()
      }
      it("can't be assigned to super types") {
        //        val parking: InvariantParking[Vehicle] = new InvariantParking[Bike]()
        //        val parking: InvariantParking[Vehicle] = new InvariantParking[Car]()
      }
    }
    describe("park") {
      describe("can park same type") {
        it("vehicles") {
          val parking: InvariantParking[Vehicle] = new InvariantParking[Vehicle]()

          val newParking: InvariantParking[Vehicle] = parking.park(new Vehicle)
        }
        it("bikes") {
          val parking: InvariantParking[Bike] = new InvariantParking[Bike]()

          val newParking: InvariantParking[Bike] = parking.park(new Bike)
        }
        it("cars") {
          val parking: InvariantParking[Car] = new InvariantParking[Car]()

          val newParking: InvariantParking[Car] = parking.park(new Car)
        }
      }
      describe("can park sub types") {
        it("bike") {
          val parking: InvariantParking[Vehicle] = new InvariantParking[Vehicle]()

          val newParking: InvariantParking[Vehicle] = parking.park(new Bike)
        }
        it("car") {
          val parking: InvariantParking[Vehicle] = new InvariantParking[Vehicle]()

          val newParking: InvariantParking[Vehicle] = parking.park(new Car)
        }
      }
      describe("can't park super types") {
        it("bike") {
          val parking: InvariantParking[Bike] = new InvariantParking[Bike]()

          //          parking.park(new Vehicle)
        }
        it("car") {
          val parking: InvariantParking[Car] = new InvariantParking[Car]()

          //          parking.park(new Vehicle)
        }
      }
    }
    describe("impound") {
      describe("can impound same type") {
        it("vehicles") {
          val parking: InvariantParking[Vehicle] = new InvariantParking[Vehicle]()

          val newParking: InvariantParking[Vehicle] = parking.impound(List(new Vehicle))
        }
        it("bikes") {
          val parking: InvariantParking[Bike] = new InvariantParking[Bike]()

          val newParking: InvariantParking[Bike] = parking.impound(List(new Bike))
        }
        it("cars") {
          val parking: InvariantParking[Car] = new InvariantParking[Car]()

          val newParking: InvariantParking[Car] = parking.impound(List(new Car))
        }
      }
      describe("can impound sub types") {
        it("bike") {
          val parking: InvariantParking[Vehicle] = new InvariantParking[Vehicle]()

          val newParking: InvariantParking[Vehicle] = parking.impound(List(new Bike))
        }
        it("car") {
          val parking: InvariantParking[Vehicle] = new InvariantParking[Vehicle]()

          val newParking: InvariantParking[Vehicle] = parking.impound(List(new Car))
        }
      }
      describe("can't impound super types") {
        it("bike") {
          val parking: InvariantParking[Bike] = new InvariantParking[Bike]()

          //          parking.impound(List(new Vehicle))
        }
        it("car") {
          val parking: InvariantParking[Car] = new InvariantParking[Car]()

          //          parking.impound(List(new Vehicle))
        }
      }
    }
    describe("check returns same type") {
      it("vehicle") {
        val parking: InvariantParking[Vehicle] = new InvariantParking[Vehicle]()

        val newParking: List[Vehicle] = parking.check("")
      }
      it("bike") {
        val parking: InvariantParking[Bike] = new InvariantParking[Bike]()

        val newParking: List[Bike] = parking.check("")
      }
      it("car") {
        val parking: InvariantParking[Car] = new InvariantParking[Car]()

        val newParking: List[Car] = parking.check("")
      }
    }
  }

  describe("Covariant parking") {
    describe("construction") {
      describe("can be assigned to same type") {
        it("vehicles") {
          val parking: CovariantParking[Vehicle] = new CovariantParking[Vehicle]()
        }
        it("bikes") {
          val parking: CovariantParking[Bike] = new CovariantParking[Bike]()
        }
        it("cars") {
          val parking: CovariantParking[Car] = new CovariantParking[Car]()
        }
      }
      it("can't be assigned to sub types") {
        //        val parking: CovariantParking[Bike] = new CovariantParking[Vehicle]()
        //        val parking: CovariantParking[Car] = new CovariantParking[Vehicle]()
      }
      describe("can be assigned to super types") {
        it("bikes") {
          val parking: CovariantParking[Vehicle] = new CovariantParking[Bike]()
        }
        it("cars") {
          val parking: CovariantParking[Vehicle] = new CovariantParking[Car]()
        }
      }
    }
    describe("park") {
      describe("can park same type") {
        it("vehicles") {
          val parking: CovariantParking[Vehicle] = new CovariantParking[Vehicle]()

          val newParking: CovariantParking[Vehicle] = parking.park(new Vehicle)
        }
        it("bikes") {
          val parking: CovariantParking[Bike] = new CovariantParking[Bike]()

          val newParking: CovariantParking[Bike] = parking.park(new Bike)
        }
        it("cars") {
          val parking: CovariantParking[Car] = new CovariantParking[Car]()

          val newParking: CovariantParking[Car] = parking.park(new Car)
        }
      }
      describe("can park sub types") {
        it("bike") {
          val parking: CovariantParking[Vehicle] = new CovariantParking[Vehicle]()

          val newParking: CovariantParking[Vehicle] = parking.park(new Bike)
        }
        it("car") {
          val parking: CovariantParking[Vehicle] = new CovariantParking[Vehicle]()

          val newParking: CovariantParking[Vehicle] = parking.park(new Car)
        }
      }

      describe("can park super types") {
        it("bike") {
          val parking: CovariantParking[Bike] = new CovariantParking[Bike]()

          val newParking: CovariantParking[Vehicle] = parking.park(new Vehicle)
        }
        it("car") {
          val parking: CovariantParking[Car] = new CovariantParking[Car]()

          val newParking: CovariantParking[Vehicle] = parking.park(new Vehicle)
        }
      }
    }
    describe("impound") {
      describe("can impound exactly same type") {
        it("vehicles") {
          val parking: CovariantParking[Vehicle] = new CovariantParking[Vehicle]()

          val newParking: CovariantParking[Vehicle] = parking.impound(List(new Vehicle))
        }
        it("bikes") {
          val parking: CovariantParking[Bike] = new CovariantParking[Bike]()

          val newParking: CovariantParking[Vehicle] = parking.impound(List(new Bike))
        }
        it("cars") {
          val parking: CovariantParking[Car] = new CovariantParking[Car]()

          val newParking: CovariantParking[Vehicle] = parking.impound(List(new Car))
        }
      }
      describe("can impound sub types") {
        it("bike") {
          val parking: CovariantParking[Vehicle] = new CovariantParking[Vehicle]()

          val newParking: CovariantParking[Vehicle] = parking.impound(List(new Bike))
        }
        it("car") {
          val parking: CovariantParking[Vehicle] = new CovariantParking[Vehicle]()

          val newParking: CovariantParking[Vehicle] = parking.impound(List(new Car))
        }
      }
      describe("can impound super types") {
        it("bike") {
          val parking: CovariantParking[Bike] = new CovariantParking[Bike]()

          val newParking: CovariantParking[Vehicle] = parking.impound(List(new Vehicle))
        }
        it("car") {
          val parking: CovariantParking[Car] = new CovariantParking[Car]()

          val newParking: CovariantParking[Vehicle] = parking.impound(List(new Vehicle))
        }
      }
    }
    describe("check returns same type") {
      it("vehicle") {
        val parking: CovariantParking[Vehicle] = new CovariantParking[Vehicle]()

        val newParking: List[Vehicle] = parking.check("")
      }
      it("bike") {
        val parking: CovariantParking[Bike] = new CovariantParking[Bike]()

        val newParking: List[Bike] = parking.check("")
      }
      it("car") {
        val parking: CovariantParking[Car] = new CovariantParking[Car]()

        val newParking: List[Car] = parking.check("")
      }
    }
  }

  describe("Contravariant parking") {
    describe("construction") {
      describe("can be assigned to same type") {
        it("vehicles") {
          val parking: ContravariantParking[Vehicle] = new ContravariantParking[Vehicle]()
        }
        it("bikes") {
          val parking: ContravariantParking[Bike] = new ContravariantParking[Bike]()
        }
        it("cars") {
          val parking: ContravariantParking[Car] = new ContravariantParking[Car]()
        }
      }
      describe("can be assigned to sub types") {
        it("bikes") {
          val parking: ContravariantParking[Bike] = new ContravariantParking[Vehicle]()
        }
        it("cars") {
          val parking: ContravariantParking[Car] = new ContravariantParking[Vehicle]()
        }
      }
      it("can't be assigned to super types") {
        //        val parking: ContravariantParking[Vehicle] = new ContravariantParking[Bike]()
        //        val parking: ContravariantParking[Vehicle] = new ContravariantParking[Car]()
      }
    }
    describe("park") {
      describe("can park same type") {
        it("vehicles") {
          val parking: ContravariantParking[Vehicle] = new ContravariantParking[Vehicle]()

          val newParking: ContravariantParking[Vehicle] = parking.park(new Vehicle)
        }
        it("bikes") {
          val parking: ContravariantParking[Bike] = new ContravariantParking[Bike]()

          val newParking: ContravariantParking[Bike] = parking.park(new Bike)
        }
        it("cars") {
          val parking: ContravariantParking[Car] = new ContravariantParking[Car]()

          val newParking: ContravariantParking[Car] = parking.park(new Car)
        }
      }
      describe("can park sub types") {
        it("bike") {
          val parking: ContravariantParking[Vehicle] = new ContravariantParking[Vehicle]()

          val newParking: ContravariantParking[Vehicle] = parking.park(new Bike)
        }
        it("car") {
          val parking: ContravariantParking[Vehicle] = new ContravariantParking[Vehicle]()

          val newParking: ContravariantParking[Vehicle] = parking.park(new Car)
        }
      }

      describe("can't park super types") {
        it("bike") {
          val parking: ContravariantParking[Bike] = new ContravariantParking[Bike]()

//          val newParking: ContravariantParking[Bike] = parking.park(new Vehicle)
        }
        it("car") {
          val parking: ContravariantParking[Car] = new ContravariantParking[Car]()

//          val newParking: ContravariantParking[Car] = parking.park(new Vehicle)
        }
      }
    }
    describe("impound") {
      describe("can impound same type") {
        it("vehicles") {
          val parking: ContravariantParking[Vehicle] = new ContravariantParking[Vehicle]()

          val newParking: ContravariantParking[Vehicle] = parking.impound(List(new Vehicle))
        }
        it("bikes") {
          val parking: ContravariantParking[Bike] = new ContravariantParking[Bike]()

          val newParking: ContravariantParking[Bike] = parking.impound(List(new Bike))
        }
        it("cars") {
          val parking: ContravariantParking[Car] = new ContravariantParking[Car]()

          val newParking: ContravariantParking[Car] = parking.impound(List(new Car))
        }
      }
      describe("can impound sub types") {
        it("bike") {
          val parking: ContravariantParking[Vehicle] = new ContravariantParking[Vehicle]()

          val newParking: ContravariantParking[Bike] = parking.impound(List(new Bike))
        }
        it("car") {
          val parking: ContravariantParking[Vehicle] = new ContravariantParking[Vehicle]()

          val newParking: ContravariantParking[Car] = parking.impound(List(new Car))
        }
      }
      describe("can't impound super types") {
        it("bike") {
          val parking: ContravariantParking[Bike] = new ContravariantParking[Bike]()

//          val newParking: ContravariantParking[Vehicle] = parking.impound(List(new Vehicle))
        }
        it("car") {
          val parking: ContravariantParking[Car] = new ContravariantParking[Car]()

//          val newParking: ContravariantParking[Vehicle] = parking.impound(List(new Vehicle))
        }
      }
    }
    describe("check") {
      describe("same type") {
        it("vehicle") {
          val parking: ContravariantParking[Vehicle] = new ContravariantParking[Vehicle]()

          val newParking: List[Vehicle] = parking.check("")
        }
        it("bike") {
          val parking: ContravariantParking[Bike] = new ContravariantParking[Bike]()

          val newParking: List[Bike] = parking.check("")
        }
        it("car") {
          val parking: ContravariantParking[Car] = new ContravariantParking[Car]()

          val newParking: List[Car] = parking.check("")
        }
      }
    }
  }
}
