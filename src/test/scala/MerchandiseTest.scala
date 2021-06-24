import com.myCompany.merchandise.{Caps, Merchandise, Repair, Shoes}
import com.myCompany.client.{Purchase, WebsiteClient}
import com.myCompany.payment.{BankTransfer, Check, CreditCard}
import com.myCompany.store.WebsiteStore
import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MerchandiseTest extends AnyFlatSpec with should.Matchers with GivenWhenThen{
  val price = 4
  val shoesName = "marco"
  val shoesSize = "43"
  val shoesFabric = "cotton"
  val merchandise: Shoes = Shoes(shoesName, price = price, size = shoesSize, fabric = shoesFabric)
  val merchandise1: Caps = Caps("polo", price = 10, size = "M", fabric = "polyester")
  val merchandise2: Repair = Repair("jeans", price = 20)
  val purchase = new Purchase(merchandise, 2, 12)
  val purchase1 = new Purchase(merchandise1, 2, 12)
  val client: WebsiteClient = WebsiteClient("Rick", verified = true, Set(purchase, purchase1))
  val websiteStore: WebsiteStore = WebsiteStore(Map(merchandise -> 1, merchandise1 -> 3, merchandise2 -> 0), Set(client))

  "First Exercise" should "Implement a com.myCompany.merchandise.Merchandise Class" in {
    Given("A merchandise to implement")
    val name = "testMerchandise"
    val price = 1.4d
    When("Implementing the class merchandise")
    Then("the implementation should have worked")
    price shouldBe 1.4d
  }

  "second Exercise" should "promote parameters to fields" in {
    Given("A merchandise to implement")
    When("Implementing the class merchandise")
    Then("The promotion should work")
    merchandise.price shouldBe price
    merchandise.name shouldBe shoesName
    And("the description field should be correctly constructed")
    merchandise.description.contains(price.toString) shouldBe true
    merchandise.description.contains(shoesName) shouldBe true
  }

  "third Exercise" should "create a purchase with promoter parameters" in {
    Given("A merchandise to implement")
    When("Creating a purchase")
    val purchase = new Purchase(merchandise, 23, 12)
    Then("purchase should have promoted fields")
    purchase.hours shouldBe 23
    purchase.minutes shouldBe 12
  }

  "fourth Exercise" should "correctly calculates minutes" in {
    Given("A merchandise and a purchase")
    val purchase = new Purchase(merchandise, 2, 12)
    When("Calculating purchase time in minutes")
    val purchaseTime = 2*60 + 12
    Then("it shoud be the same as in purchase's asMinutes field")
    purchase.asMinutes shouldEqual purchaseTime
  }

  "fifth Exercise" should "define a isCheapMethod" in {
    Given("A merchandise with a price of 12")
    When("defining the cheap limit to 11")
    Then("the merchandise shouldn't be cheap")
    merchandise.isCheap(price - 1) shouldBe false
    merchandise.isCheap(price + 1) shouldBe true
  }

  "sixth Exercise" should "define a % infix operator for promotions" in {
    Given("A merchandise with a price of 100")
    val purchase = new Purchase(merchandise, 2, 12)
    When("defining a 50% promotion")
    val promotion = 0.5
    Then("the returned price should be half the original")
    purchase % promotion shouldBe price * promotion
  }

  "seventh Exercise" should "define default arguments" in {
    Given("A purchase without hours and minutes")
    val purchase = new Purchase(merchandise)
    Then("default hours and minutes should be zéro")
    purchase.hours shouldBe 0
    purchase.minutes shouldBe 0
  }
  it should "define a cheaperThan method" in {
    Given("two merchandise with prices of 10 and 20")
    When("Comparing both merchandises")
    val isCheaper = merchandise1 < merchandise2
    Then("result should be true")
    assert(isCheaper, "Method isCheaperThan isn't working properly")
  }

//  "ninth exercise" should "be able to make a purchase in a store" in {
//    Given("A WebsiteStore instance")
//    val merchandise = new Merchandise("polo", price = 34)
//    val websiteStore = WebsiteStore(merchandise, 10)
//    val client = WebsiteClient("Rick", verified = true)
//    When("Making a purchase or cancelling one")
//    val websiteStoreMakePurchase = Purchase.makePurchase(websiteStore, client)
//    val websiteStoreCancelPurchase = Purchase.cancelPurchase(websiteStore, client)
//    Then("the quantity must have been lower in a purchase")
//    websiteStoreMakePurchase.quantity shouldEqual websiteStore.quantity - 1
//    websiteStoreCancelPurchase.quantity shouldEqual websiteStore.quantity + 1
//  }

  "tenth exercise" should "have preconditions for hours and minutes" in {
    Given("hours above 23 and minutes below 0")
    When("creating a new Purchase")
    Then("an IllegalArgumentException must be thrown")
    assertThrows[IllegalArgumentException](new Purchase(merchandise, 55, 0))
    assertThrows[IllegalArgumentException](new Purchase(merchandise, -15, 0))
    assertThrows[IllegalArgumentException](new Purchase(merchandise, 0, 120))
    assertThrows[IllegalArgumentException](new Purchase(merchandise, 0, -3))
    new Purchase(merchandise, 0, 0).asMinutes shouldEqual 0
  }

//  "thirteenth exercise" should "test that a client cannot make a purchase unless verified" in {
//    Given("A client that isn't verified")
//    val merchandise = new Merchandise("marco", price = 4)
//    val websiteStore = WebsiteStore(merchandise, 1)
//    val client = WebsiteClient("Rick", verified = false)
//    When("Making a purchase")
//    val newWebsiteStore = Purchase.makePurchase(websiteStore, client)
//    Then("The quantity shouldn't change")
//    newWebsiteStore.quantity shouldEqual websiteStore.quantity
//  }

  "fourteenth exercice" should "define a set of purchase for websiteClient and redefine WebsiteStore" in {
    Given("A websiteStore and two merchandises")
    val websiteStore = WebsiteStore(Map(merchandise -> 1), Set(client))
    When("adding merchandises")
    val newStore = websiteStore.addMerchandise(merchandise1, 10)
    Then("merchandise map must be updated")
    newStore.merchandises(merchandise1) shouldEqual 10

    Given("a purchase")
    val purchase1 = new Purchase(merchandise1, 2, 12)
    When("adding a purchase to a client")
    val clientAfterPurchase = client.addPurchase(purchase1)
    val clientRemovePurchase = client.removePurchase(purchase)
    Then("the purchase should exist in client portfolio")
    assert(clientAfterPurchase.purchases.contains(purchase1))
    assert(!clientRemovePurchase.purchases.contains(purchase))
  }

  "fifteenth exercice" should "be abe to reset quantities in a website store" in {
    Given("a websiteStore with quantities not null")
    When("resetting quantities")
    val newWebsiteStore = websiteStore.resetQuantities
    Then("quantities must be zero")
    newWebsiteStore.merchandises.foreach(_._2 shouldEqual 0)
  }

  "sixteenth exercise" should "be able to list sold products" in {
    Given("a websiteStore")
    When("calling soldProducts")
    val soldProducts = websiteStore.soldProducts
    Then("a list of sold products should be returned")
    soldProducts should contain(merchandise.name)
    soldProducts should contain(merchandise1.name)
  }

  "seventeenth exercise" should "be able to filter out empty stock" in {
    Given("a websiteStore")
    When("calling availableMerc")
    val notNullMerch = websiteStore.availableMerc
    Then("only available product must be in the Map")
    notNullMerch.foreach(_._2 shouldNot equal(0))
  }

  "nineteenth exercise" should "have defined Shoe and Caps" in {
    Given("a shoe and a Cap")
    Then("description must have been overriden for both")
    merchandise1.description.toLowerCase.split(" ") should contain("caps")
    merchandise.description.toLowerCase.split(" ") should contain("shoes")
  }

  "20th exercise" should "defined an ADT" in {
    val check = Check(10)
    val bankTransfer = BankTransfer(1)
    val creditCard = CreditCard(0.5)
    val creditCard1 = CreditCard(1.5)

    check.accepted shouldBe false
    bankTransfer.accepted shouldBe true
    creditCard.accepted shouldBe false
    creditCard1.accepted shouldBe true
  }

  "21th exercice" should "Create a Clothes traits" in {
    merchandise.size shouldEqual shoesSize
    merchandise.fabric shouldEqual shoesFabric
  }
}
