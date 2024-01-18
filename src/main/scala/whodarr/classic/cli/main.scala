package whodarr.classic.cli

import scala.io.StdIn

@main
def main(): Unit = {
  println("Hello world!")

  println("Enter path to folder> ")
  val dirpath = StdIn.readLine()

  println("Enter Serial designation> ")
  val designation = StdIn.readLine()

  println("Enter Serial episode offset> ")
  val serialEpisodeOffset = StdIn.readInt


}