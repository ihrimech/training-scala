package com.myCompany.merchandise

case class Caps(override val name: String, override val price: Double) extends Merchandise(name, price) {
  override val description: String = s"Caps - $name at ${price}€"
}
