package com.myCompany.client

import com.myCompany.merchandise.Merchandise
import com.myCompany.payment.{BankTransfer, Check, CreditCard, PaymentMethods}
import com.myCompany.store.WebsiteStore

class Purchase(val merchandise: Merchandise, val hours: Int = 0, val minutes: Int = 0) {
  require(hours >= 0 && hours < 24, "hours must be between 0 and 23")
  require(minutes >= 0 && minutes < 59, "minutes must be between 0 and 59")

  val asMinutes: Int = hours * 60 + minutes

  def %(percentage: Double): Double = promotion(percentage)

  def promotion(percentage: Double): Double = merchandise.price * percentage

  def makePayment(paymentMethod: PaymentMethods): String = (paymentByCheck orElse paymentByCard orElse paymentByBankTransfer)(paymentMethod)

  val paymentByCard = new PartialFunction[PaymentMethods, String] {
    override def isDefinedAt(x: PaymentMethods): Boolean = x match {
      case _:CreditCard => true
      case _ => false
    }

    override def apply(v1: PaymentMethods): String = "Payment accepted"
  }

  val paymentByCheck = new PartialFunction[PaymentMethods, String] {
    override def isDefinedAt(x: PaymentMethods): Boolean = x match {
      case _:Check => true
      case _ => false
    }

    override def apply(v1: PaymentMethods): String = "Payment refused"
  }

  val paymentByBankTransfer = new PartialFunction[PaymentMethods, String] {
    override def isDefinedAt(x: PaymentMethods): Boolean = x match {
      case _:BankTransfer => true
      case _ => false
    }

    override def apply(v1: PaymentMethods): String = "Payment pending"
  }
}
