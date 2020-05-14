package advancedfp

object Chatbot extends App {
  val reply: PartialFunction[String, String] = {
    case "Hi" => "Hello"
    case "Bye" => "Ciao"
    case _ => "I don't understand you"
  }

  scala.io.Source.stdin.getLines()
    .map(reply)
    .foreach(println)
}
