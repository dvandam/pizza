package pizza

import scala.annotation.implicitNotFound

sealed trait Ingredient
trait Dough extends Ingredient
trait Sauce extends Ingredient
trait Topping extends Ingredient
trait Pepper extends Ingredient
trait Cheese extends Ingredient

case class Pizza(ingredients: Ingredient*)

object Pizza extends App {
  type RequiredIngredients = Dough with Sauce with Topping

  @implicitNotFound(msg = "Not all required ingredients are available in: ${I}")
  type ContainsRequiredIngredients[Ingredients] = Ingredients <:< RequiredIngredients

  class Builder[Ingredients <: Ingredient] private (ingredients: Seq[Ingredient]) {
    def add(ingredient: Ingredient) = new Builder[Ingredients with ingredient.type](ingredients ++ Seq(ingredient))
    def build(implicit evidence: ContainsRequiredIngredients[Ingredients]):Pizza = new Pizza(ingredients: _*)
  }

  object Builder {
    def apply(): Builder[Ingredient] = new Builder[Ingredient](Seq.empty)
  }
}
