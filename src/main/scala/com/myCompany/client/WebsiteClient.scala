package com.myCompany.client

case class WebsiteClient(name: String, verified: Boolean, purchases: Set[Purchase]) {
  def addPurchase(purchase: Purchase): WebsiteClient = WebsiteClient(name, verified, purchases + purchase)

  def removePurchase(purchase: Purchase): WebsiteClient = WebsiteClient(name, verified, purchases - purchase)
}