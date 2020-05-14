package advancedfp

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers

class ChatbotSpec extends AnyFunSpec with Matchers {

  describe("speak") {
    it ("should reply 'Hi' when input is 'Hello'") {
      val reply = Chatbot.reply("Hi")

      reply should be ("Hello")
    }
    it ("should reply 'Ciao' when input is 'Bye'") {
      val reply = Chatbot.reply("Bye")

      reply should be ("Ciao")
    }
    it ("should reply 'I don't understand you' when input is anything else") {
      val reply = Chatbot.reply("hahahaha")

      reply should be ("I don't understand you")
    }
  }
}
