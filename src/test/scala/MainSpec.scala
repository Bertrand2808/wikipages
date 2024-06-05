// src/test/scala/MainSpec.scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import play.api.libs.json._
import scalaj.http._

class MainSpec extends AnyFlatSpec with Matchers {

  "A simple test" should "pass when everything is OK" in {
    val sum = 1 + 1
    sum shouldEqual 2
  }
  
  "formatUrl" should "format the URL correctly" in {
    val keyword = "scala"
    val limit = 10
    val expectedUrl = s"https://en.wikipedia.org/w/api.php?action=query&format=json&prop=&sroffset=0&list=search&srsearch=$keyword&srlimit=$limit"
    
    Main.formatUrl(keyword, limit) shouldEqual expectedUrl
  }

  "parseJson" should "extract titles from JSON correctly" in {
    val jsonString = """
      {
        "query": {
          "search": [
            { "title": "Scala (programming language)" },
            { "title": "Scala (opera)" },
            { "title": "Scala (software)" }
          ]
        }
      }
    """
    val expectedTitles = Seq("Scala (programming language)", "Scala (opera)", "Scala (software)")
    
    val pages = Main.parseJson(jsonString)
    val titles = pages.map(_.title)
    
    titles shouldEqual expectedTitles
  }

  "totalWords" should "return 0 for an empty list" in {
    val pages = Seq.empty[WikiPage]
    Main.totalWords(pages) shouldEqual 0
  }

  it should "return the correct total word count for a non-empty list" in {
    val pages = Seq(
      WikiPage("Page1", Some(100)),
      WikiPage("Page2", Some(200)),
      WikiPage("Page3", Some(300))
    )
    Main.totalWords(pages) shouldEqual 600
  }

  "parseArguments" should "return None for non-parsable arguments" in {
    val args = Array("--invalid", "arg")
    Main.parseArguments(args) shouldBe None
  }

  it should "parse arguments with a keyword correctly" in {
    val args = Array("keyword")
    Main.parseArguments(args) shouldBe Some(Config(keyword = "keyword", limit = 10))
  }

  it should "parse arguments with a keyword and limit correctly" in {
    val args = Array("-l", "5", "keyword")
    Main.parseArguments(args) shouldBe Some(Config(keyword = "keyword", limit = 5))
  }

  object MockHttpUtils extends HttpUtils {
    override def get(url: String): HttpRequest = new HttpRequest("mock-url", "GET", null, Seq(), Seq(), Seq(), None, "UTF-8", 4096, _.toString, false, None) {
      override def asString: HttpResponse[String] = {
        HttpResponse("""{"query": {"search": [{"title": "Mock Page 1", "wordcount": 100}]}}""", 200, Map.empty)
      }
    }
  }

  "getPages" should "return the correct response body for a successful request" in {
    val url = "https://mockurl"
    Main.getPages(url, MockHttpUtils) shouldEqual Right("""{"query": {"search": [{"title": "Mock Page 1", "wordcount": 100}]}}""")
  }
}
