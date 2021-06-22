package com.myCompany.client

import com.myCompany.merchandise.Merchandise
import com.myCompany.store.WebsiteStore

class Purchase(val merchandise: Merchandise, val hours: Int = 0, val minutes: Int = 0) {
  require(hours >= 0 && hours < 24, "hours must be between 0 and 23")
  require(minutes >= 0 && minutes < 59, "minutes must be between 0 and 59")

  val asMinutes: Int = hours * 60 + minutes

  def %(percentage: Double): Double = promotion(percentage)

  def promotion(percentage: Double): Double = merchandise.price * percentage
}

object Purchase {

  def makePurchase(websiteStore: WebsiteStore, client: WebsiteClient): WebsiteStore = {
    if(client.verified) websiteStore.copy(quantity = websiteStore.quantity - 1)
    else websiteStore
  }

  def cancelPurchase(websiteStore: WebsiteStore, client: WebsiteClient): WebsiteStore = {
    if(client.verified) websiteStore.copy(quantity = websiteStore.quantity + 1)
    else websiteStore
  }
}
