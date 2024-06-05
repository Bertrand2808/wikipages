import scalaj.http._

trait HttpUtils {
  def get(url: String): HttpRequest
}

object RealHttpUtils extends HttpUtils {
  override def get(url: String): HttpRequest = Http(url)
}