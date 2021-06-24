package com.myCompany.payment

abstract class PaymentMethods(amount: Double) {
  val accepted = false
}

case class CreditCard(amount: Double) extends PaymentMethods(amount) {
  override val accepted: Boolean = amount > 1
}

case class BankTransfer(amount: Double) extends PaymentMethods(amount) {
  override val accepted: Boolean = true
}

case class Check(amount: Double) extends PaymentMethods(amount) {
  override val accepted: Boolean = false
}

