package com.myCompany.store

import com.myCompany.client.WebsiteClient
import com.myCompany.merchandise.Merchandise

case class WebsiteStore(merchandises: Map[Merchandise, Int], clients: Set[WebsiteClient]) {
  def addMerchandise(merchandise: Merchandise, quantity: Int) : WebsiteStore = WebsiteStore(merchandises + (merchandise -> quantity), clients)

  def resetQuantities: WebsiteStore = WebsiteStore(merchandises.map(merchandise => merchandise._1 -> 0), clients)

  def soldProducts: Set[String] = for {
    client <- clients
    purchase <- client.purchases
  } yield purchase.merchandise.name

  def availableMerc: Map[Merchandise, Int] = merchandises.filter(_._2 > 0)
}

