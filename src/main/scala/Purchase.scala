class Purchase(val merchandise: Merchandise, val hours: Int, val minutes: Int) {
  // TODO verifier que hours est comprise entre 0 et 23
  // TODO verifier que minutes est comprise entre 0 et 59
  val asMinutes: Int = hours * 60 + minutes
}