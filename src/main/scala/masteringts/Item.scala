package masteringts

trait ItemLike {
  type Key
}

trait Item[K] extends ItemLike {
  override type Key = K
}

class IntItem extends Item[Int]
class StringItem extends Item[String]

object Item {
  def get[ItemType <: ItemLike](key: ItemType#Key): ItemType = ???

  get[IntItem](1)
  get[StringItem]("foo")
//  get[IntItem]("foo")
//  get[StringItem](1)
}

