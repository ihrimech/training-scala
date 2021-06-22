import com.myCompany.merchandise.Merchandise
import com.myCompany.purchase.Purchase
import com.myCompany.store.WebsiteStore
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MerchandiseTest extends AnyFlatSpec with should.Matchers with GivenWhenThen{

  "First Exercise" should "Implement a com.myCompany.merchandise.Merchandise Class" in {
    Given("A merchandise to implement")
    val name = "testMerchandise"
    val price = 1.4d
    When("Implementing the class merchandise")
    val merchandise = new Merchandise(name = name, price = price)
    Then("the implementation should have worked")
    price shouldBe 1.4d
  }

  "second Exercise" should "promote parameters to fields" in {
    Given("A merchandise to implement")
    val name = "testMerchandise"
    val price = 1.4d
    When("Implementing the class merchandise")
    val merchandise = new Merchandise(name = name, price = price)
    Then("The promotion should work")
    merchandise.price shouldBe price
    merchandise.name shouldBe name
    And("the description field should be correctly constructed")
    merchandise.description.contains(price.toString) shouldBe true
    merchandise.description.contains(name) shouldBe true
  }

  "third Exercise" should "create a purchase with promoter parameters" in {
    Given("A merchandise to implement")
    val merchandise = new Merchandise("testMerchandise", price = 12)
    When("Creating a purchase")
    val purchase = new Purchase(merchandise, 23, 12)
    Then("purchase should have promoted fields")
    purchase.hours shouldBe 23
    purchase.minutes shouldBe 12
  }

  "fourth Exercise" should "correctly calculates minutes" in {
    Given("A merchandise and a purchase")
    val merchandise = new Merchandise("testMerchandise", price = 12)
    val purchase = new Purchase(merchandise, 2, 12)
    When("Calculating purchase time in minutes")
    val purchaseTime = 2*60 + 12
    Then("it shoud be the same as in purchase's asMinutes field")
    purchase.asMinutes shouldEqual purchaseTime
  }

  "fifth Exercise" should "define a isCheapMethod" in {
    Given("A merchandise with a price of 12")
    val merchandise = new Merchandise("testMerchandise", price = 12)
    When("defining the cheap limit to 11")
    val cheapLimit = 11
    Then("the merchandise shouldn't be cheap")
    merchandise.isCheap(cheapLimit) shouldBe false
    merchandise.isCheap(13) shouldBe true
  }

  "sixth Exercise" should "define a % infix operator for promotions" in {
    Given("A merchandise with a price of 100")
    val merchandise = new Merchandise("testMerchandise", price = 100)
    val purchase = new Purchase(merchandise, 2, 12)
    When("defining a 50% promotion")
    val promotion = 0.5
    Then("the returned price should be half the original")
    purchase % promotion shouldBe 50
  }

  "seventh Exercise" should "define default arguments" in {
    Given("A purchase without hours and minutes")
    val merchandise = new Merchandise("testMerchandise", price = 100)
    val purchase = new Purchase(merchandise)
    Then("default hours and minutes should be zéro")
    purchase.hours shouldBe 0
    purchase.minutes shouldBe 0
  }
  it should "define a cheaperThan method" in {
    Given("two merchandise with prices of 10 and 20")
    val merchandise1 = new Merchandise("testMerchandise", price = 10)
    val merchandise2 = new Merchandise("testMerchandise2", price = 20)
    When("Comparing both merchandises")
    val isCheaper = merchandise1 < merchandise2
    Then("result should be true")
    assert(isCheaper, "Method isCheaperThan isn't working properly")
  }

  "ninth exercise" should "be able to make a purchase in a store" in {
    Given("A WebsiteStore instance")
    val merchandise = new Merchandise("polo", price = 34)
    val websiteStore = new WebsiteStore(merchandise, 10)
    When("Making a purchase or cancelling one")
    val websiteStoreMakePurchase = Purchase.makePurchase(websiteStore)
    val websiteStoreCancelPurchase = Purchase.cancelPurchase(websiteStore)
    Then("the quantity must have been lower in a purchase")
    websiteStoreMakePurchase.quantity shouldEqual websiteStore.quantity - 1
    websiteStoreCancelPurchase.quantity shouldEqual websiteStore.quantity + 1
  }

  "tenth exercise" should "have preconditions for hours and minutes" in {
    Given("hours above 23 and minutes below 0")
    val merchandise = new Merchandise("polo", price = 34)
    When("creating a new Purchase")
    Then("an IllegalArgumentException must be thrown")
    assertThrows[IllegalArgumentException](new Purchase(merchandise, 55, 0))
    assertThrows[IllegalArgumentException](new Purchase(merchandise, -15, 0))
    assertThrows[IllegalArgumentException](new Purchase(merchandise, 0, 120))
    assertThrows[IllegalArgumentException](new Purchase(merchandise, 0, -3))
    new Purchase(merchandise, 0, 0).asMinutes shouldEqual 0
  }
}
