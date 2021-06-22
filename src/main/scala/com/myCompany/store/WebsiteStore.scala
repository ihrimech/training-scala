package com.myCompany.store

import com.myCompany.merchandise.Merchandise

case class WebsiteStore(merchandises: Map[Merchandise, Int]) {
  def addMerchandise(merchandise: Merchandise, quantity: Int) : WebsiteStore = WebsiteStore(merchandises + (merchandise -> quantity))

  def resetQuantities: WebsiteStore = WebsiteStore(merchandises.map(merchandise => merchandise._1 -> 0))
}

