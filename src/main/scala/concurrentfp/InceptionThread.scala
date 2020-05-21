package concurrentfp

object InceptionThread extends App {

  def apply(times: Int)(callback: String => Unit): Thread = new Thread(() => {
    if (times > 1) {
      val thread = InceptionThread(times - 1)(callback)
      thread.start()
      thread.join()
    }
    callback(times.toString)
  })

  InceptionThread(50)(System.out.println).start()
}
