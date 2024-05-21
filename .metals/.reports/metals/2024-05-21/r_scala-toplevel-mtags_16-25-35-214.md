error id: file:///E:/Documents_HDD/ssd/Cours_ESGI/M1/Scala/Exam_Scala/wikipages/src/main/scala/Main.scala:[1319..1322) in Input.VirtualFile("file:///E:/Documents_HDD/ssd/Cours_ESGI/M1/Scala/Exam_Scala/wikipages/src/main/scala/Main.scala", "import scopt.OParser
import scalaj.http._
import play.api.libs.json.{Json, JsArray}

case class Config(limit: Int = 10, keyword: String = "")

object Main extends App {
  println("Les dépendances sont bien ajoutées et importées!")
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

  def getPages(url: String): Either[Int, String] = {
    val response = Http(url).asString
    response.code match {
      case 200 => Right(response.body)
      case errorCode => Left(errorCode)
    }
  }

  def 

  def run(config: Config): Unit = {
    val url = formatUrl(config.keyword, config.limit)
    getPages(url) match {
      case Left(errorCode) => println(s"Error occurred with code: $errorCode")
      case Right(body) => println(body)
    }
  }
}
")
file:///E:/Documents_HDD/ssd/Cours_ESGI/M1/Scala/Exam_Scala/wikipages/src/main/scala/Main.scala
file:///E:/Documents_HDD/ssd/Cours_ESGI/M1/Scala/Exam_Scala/wikipages/src/main/scala/Main.scala:47: error: expected identifier; obtained def
  def run(config: Config): Unit = {
  ^
#### Short summary: 

expected identifier; obtained def