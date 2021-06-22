class Merchandise (val name: String, val price: Double) {
  val description: String = name + " " + price

  def isCheap(cheapLimit: Double): Boolean = price < cheapLimit

  def isCheaperThan(that: Merchandise): Boolean = this.price < that.price

  def <(that: Merchandise): Boolean = isCheaperThan(that)
}
