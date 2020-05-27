package advancedfp

class Chatbot {
  def reply: PartialFunction[String, String] = {
    case "Hi" => "Hello"
    case "Bye" => "Ciao"
    case _ => "I don't understand you"
  }
}
object Chatbot extends App {
  val bot = new Chatbot

  scala.io.Source.stdin.getLines()
    .map(bot.reply)
    .foreach(println)
}
