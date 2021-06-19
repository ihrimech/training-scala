import org.scalatest.GivenWhenThen
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class MerchandiseTest extends AnyFlatSpec with should.Matchers with GivenWhenThen{

  "First Exercice" should "Implement a Merchandise Class" in {
    Given("A merchandise to implement")
    val name = "testMerchandise"
    val price = 1.4d
    When("Implementing the class merchandise")
    val merchandise = new Merchandise(name = name, price = price)
    Then("the implementation should have worked")
    price shouldBe 1.4d
  }
}
