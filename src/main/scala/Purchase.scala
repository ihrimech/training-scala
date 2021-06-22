class Purchase(val merchandise: Merchandise, val hours: Int = 0, val minutes: Int = 0) {
  // TODO verifier que hours est comprise entre 0 et 23
  // TODO verifier que minutes est comprise entre 0 et 59
  val asMinutes: Int = hours * 60 + minutes

  def %(percentage: Double): Double = promotion(percentage)

  def promotion(percentage: Double): Double = merchandise.price * percentage
}