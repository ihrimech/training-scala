package com.myCompany

import com.myCompany.merchandise.Merchandise
import com.myCompany.client.{Purchase, WebsiteClient}
import com.myCompany.store.WebsiteStore

object MainApplication extends App {
  val merchandise = new Merchandise(
    name = "jeans",
    price = 40.0
  )
  val purchase = new Purchase(merchandise, 2, 12)
  val client: WebsiteClient = WebsiteClient("Rick", verified = true, Set(purchase))
  val websiteStore = WebsiteStore(Map(merchandise -> 10), Set(client))
//  val storeAfterPurchase = Purchase.makePurchase(websiteStore, client)

//  println("purchased " + storeAfterPurchase.merchandise.name + ". Quantity left: " + storeAfterPurchase.quantity)
}
