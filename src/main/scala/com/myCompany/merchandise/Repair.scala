package com.myCompany.merchandise

case class Repair(override val name: String, override val price: Double) extends Merchandise(name, price)