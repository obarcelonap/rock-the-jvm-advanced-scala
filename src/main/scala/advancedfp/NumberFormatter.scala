package advancedfp

object NumberFormatter {

  private def formatter(formatter: String)(number: Number): String = formatter format number

  val formatWithTwoDecimals: Number => String = formatter("%4.2f")
  val formatWithSixDecimals: Number => String = formatter("%8.6f")
  val formatWithTwelveDecimals: Number => String = formatter("%14.12f")

  val expandWithDifferentFormats: List[Number] => List[(String, String, String)] =
    list => list.map(num => (
      formatWithTwoDecimals(num),
      formatWithSixDecimals(num),
      formatWithTwelveDecimals(num),
    ))
}
