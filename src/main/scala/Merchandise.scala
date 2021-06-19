class Merchandise (val name: String, val price: Double) {
  val description: String = name + " " + price

  def isCheap(cheapLimit: Double): Boolean = price < cheapLimit
}
