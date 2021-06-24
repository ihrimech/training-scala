package com.myCompany.merchandise

case class Caps(
                 override val name: String,
                 override val price: Double,
                 override val size: String,
                 override val fabric: String
               )
  extends Merchandise(name, price)
    with Clothes {
  override val description: String = s"Caps - $name at ${price}€"

}
