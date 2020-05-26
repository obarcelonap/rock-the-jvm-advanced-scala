package implicits

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class PurchaseSpec extends AnyFunSpec with Matchers {

  describe("orderings") {
    val firstPurchase = Purchase(10, 5)
    val secondPurchase = Purchase(5, 20)
    val thirdPurchase = Purchase(3, 10)

    val purchases = List(
      firstPurchase,
      secondPurchase,
      thirdPurchase,
    )
    it ("should order by total price desc implicitly") {
      val purchasesByTotalPriceDesc = purchases.sorted

      purchasesByTotalPriceDesc should be (List(secondPurchase, firstPurchase, thirdPurchase))
    }
    it ("should order by numberOfUnits") {
      import OrderByNumberOfUnits._
      val purchasesByNumberOfUnits = purchases.sorted

      purchasesByNumberOfUnits should be (List(thirdPurchase, secondPurchase, firstPurchase))
    }
    it ("should order by unitPrice") {
      import OrderByUnitPrice._
      val purchasesByUnitPrice = purchases.sorted

      purchasesByUnitPrice should be (List(firstPurchase, thirdPurchase, secondPurchase))
    }
  }
}
