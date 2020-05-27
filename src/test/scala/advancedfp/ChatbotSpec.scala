package advancedfp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ChatbotSpec extends AnyFunSpec with Matchers {

  val bot = new Chatbot
  describe("speak") {
    it ("should reply 'Hi' when input is 'Hello'") {
      val reply = bot.reply("Hi")

      reply should be ("Hello")
    }
    it ("should reply 'Ciao' when input is 'Bye'") {
      val reply = bot.reply("Bye")

      reply should be ("Ciao")
    }
    it ("should reply 'I don't understand you' when input is anything else") {
      val reply = bot.reply("hahahaha")

      reply should be ("I don't understand you")
    }
  }
}
