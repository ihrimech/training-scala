package com.myCompany.merchandise

case class Shoes(override val name: String, override val price: Double) extends Merchandise(name, price) {
  override val description: String = s"Shoes - $name at ${price}€"
}
