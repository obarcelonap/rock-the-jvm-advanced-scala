package advancedfp

object Ascii {

  val toCharacter: (Int) => String = new PartialFunction[Int, String] {
    val conversions = Map(
      (65, "A"),
      (66, "B"),
      (67, "C"),
      (68, "D"),
      (69, "E"),
    )

    override def isDefinedAt(x: Int): Boolean = conversions.contains(x)

    override def apply(v1: Int): String = conversions(v1)
  }
}
