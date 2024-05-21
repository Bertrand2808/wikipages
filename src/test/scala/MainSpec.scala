// src/test/scala/MainSpec.scala
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import play.api.libs.json._

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
}
