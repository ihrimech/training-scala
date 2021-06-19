import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MerchandiseTest extends AnyFlatSpec with should.Matchers with GivenWhenThen{

  "First Exercise" should "Implement a Merchandise Class" in {
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

}
