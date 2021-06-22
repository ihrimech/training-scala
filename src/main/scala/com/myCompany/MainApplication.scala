package com.myCompany

import com.myCompany.merchandise.Merchandise
import com.myCompany.purchase.Purchase
import com.myCompany.store.WebsiteStore

object MainApplication extends App {
  val merchandise = new Merchandise(
    name = "jeans",
    price = 40.0
  )
  val websiteStore = WebsiteStore(
    merchandise = merchandise,
    quantity = 10
  )
  val storeAfterPurchase = Purchase.makePurchase(websiteStore)

  println("purchased " + storeAfterPurchase.merchandise.name + ". Quantity left: " + storeAfterPurchase.quantity)
}
