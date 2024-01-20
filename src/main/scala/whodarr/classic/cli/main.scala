package whodarr.classic.cli

import whodarr.classic.organizer.SerialFileReorganizer
import whodarr.classic.organizer.webarchive.{WebArchiveSerialFilenameConverter, WebArchiveSerialFolder}

import scala.io.StdIn

@main
def main(): Unit = {
  println("Hello world!")

  println("Enter path to folder> ")
  val dirpath = StdIn.readLine()

  println("Enter Serial episode offset> ")
  val serialEpisodeOffset = StdIn.readInt

  println("Enter Serial number> ")
  val serialNumber = StdIn.readInt

  println("Enter Serial designation> ")
  val designation = StdIn.readLine()

  val serialFolder = WebArchiveSerialFolder(dirpath)
  val serialConverter = new WebArchiveSerialFilenameConverter(designation, serialEpisodeOffset, serialNumber)
  val reorganizer = SerialFileReorganizer(serialFolder, serialConverter)

  val reorganizedFiles = reorganizer.reorganized
  reorganizedFiles.foreach(tup => println(s"${tup._1}\t->${tup._2}"))
}