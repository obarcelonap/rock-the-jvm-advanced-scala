package masteringts

trait MList {
  type A
  def head: A
  def tail: MList

}

trait NumberList extends MList {
  type A <: Number
}

//class StringList(hd: String, tl: StringList) extends MList with NumberList {
//  override type A = String
//  override def head: String = hd
//  override def tail: MList = tl
//}

class IntegerList(hd: Integer, tl: IntegerList) extends MList with NumberList {
  override type A = Integer
  override def head: Integer = hd
  override def tail: MList = tl
}

