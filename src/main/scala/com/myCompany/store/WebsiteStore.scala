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

  def purchaseMerchandise(merchandise: Merchandise): Map[Merchandise, Int] = merchandises.map{ merchandiseQuantity =>
    merchandiseQuantity match {
      case (merch, quantity) if merchandise == merchandise && quantity > 0 => (merch, quantity - 1)
      case (m, q) => (m, q)
    }
  }

  def restockMerchandise(merchandise: Merchandise, quantity: Int): Map[Merchandise, Int] = merchandises.map{
    case (merch, quant) if merch == merchandise => (merch, quantity + quant)
    case (m, q) => (m, q)
  }

  def findMerchandise(name: String): Option[Merchandise] = {
    merchandises.find(_._1.name == name).map(_._1)
  }

  def quickPurchase(name: String): Map[Merchandise, Int] = {
    findMerchandise(name) match {
      case Some(merchandise) => purchaseMerchandise(merchandise)
      case None => merchandises
    }
  }
}

