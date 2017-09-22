package pizza

import org.scalatest.{FlatSpec, Matchers}

class PizzaSpec extends FlatSpec with Matchers {
  object Dough extends Dough
  object Sauce extends Sauce
  object Topping extends Topping
  object Pepper extends Pepper
  object Cheese extends Cheese

  "Pizza builder" should "not compile when not all required ingredients have been provided" in {
    "Pizza.Builder().build" shouldNot compile
  }

  it should "not compile when all required ingredients have been provided" in {
    "Pizza.Builder().add(Dough).add(Sauce).add(Topping).build" should compile
  }

  it should "build a Pizza" in {
    Pizza.Builder()
      .add(Dough)
      .add(Sauce)
      .add(Topping)
      .add(Cheese)
      .add(Pepper)
      .build shouldBe Pizza(Seq(Dough, Sauce, Topping, Cheese, Pepper): _*)
  }
}
