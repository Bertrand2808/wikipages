import scopt.OParser
import scalaj.http._
import play.api.libs.json.{Json, JsArray}

case class Config(limit: Int = 10, keyword: String = "")
case class WikiPage(title: String, wordCount: Option[Int])


object Main extends App {
  println("Les dependances sont bien ajoutees et importees!")

  parseArguments(args) match {
    case Some(config) => run(config)
    case _            => println("Unable to parse arguments")
  }

  def parseArguments(args: Array[String]): Option[Config] = {
    val builder = OParser.builder[Config]
    val parser = {
      import builder._
      OParser.sequence(
        programName("WikiStats"),
        opt[Int]('l', "limit")
          .action((x, c) => c.copy(limit = x))
          .text("limit is an integer property"),
        arg[String]("<keyword>")
          .required()
          .action((x, c) => c.copy(keyword = x))
          .text("keyword is a string property")
      )
    }

    OParser.parse(parser, args, Config())
  }

  def formatUrl(keyword: String, limit: Int): String = {
    s"https://en.wikipedia.org/w/api.php?action=query&format=json&prop=&sroffset=0&list=search&srsearch=$keyword&srlimit=$limit"
  }

  def getPages(url: String, httpUtils: HttpUtils): Either[Int, String] = {
    val response = httpUtils.get(url).asString
    response.code match {
      case 200       => Right(response.body)
      case errorCode => Left(errorCode)
    }
  }

  def parseJson(rawJson: String): Seq[WikiPage] = {
    val json = Json.parse(rawJson)
    val pages = (json \ "query" \ "search").asOpt[JsArray].getOrElse(Json.arr())
    pages.value.map { page =>
      val title = (page \ "title").as[String]
      val wordCount = (page \ "wordcount").asOpt[Int]
      WikiPage(title, wordCount)
    }
  }

  def totalWords(pages: Seq[WikiPage]): Int = {
    pages.foldLeft(0) { (acc, page) =>
      acc + page.wordCount.getOrElse(0)
    }
  }

  def run(config: Config): Unit = {
    val url = formatUrl(config.keyword, config.limit)
    getPages(url, RealHttpUtils) match {
      case Left(errorCode) => println(s"Error occurred with code: $errorCode")
      case Right(body) =>
        val pages = parseJson(body)
        println(s"Liste des pages trouvees : ${pages.length} au total")
        pages.foreach(page =>
          println(s"- ${page.title} / ${page.wordCount.getOrElse(0)} words")
        )

        val total = totalWords(pages)
        val average = if (pages.nonEmpty) total.toDouble / pages.length else 0

        println(s"Nombre total de mots : $total")
        println(s"Nombre moyen de mots par page : $average")
    }
  }
}
