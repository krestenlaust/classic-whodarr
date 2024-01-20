package whodarr.classic.cli

import whodarr.classic.organizer.{SerialFileReorganizer, SerialFolder}
import whodarr.classic.organizer.webarchive.{WebArchiveSerialFileFilter, WebArchiveSerialFilenameConverter}
import whodarr.classic.util.FileUtility

import java.nio.file.Paths
import scala.io.StdIn

@main
def main(): Unit = {
  println("Enter path to folder> ")
  val dirpath = StdIn.readLine()

  println("Enter Serial episode offset> ")
  val serialEpisodeOffset = StdIn.readInt

  println("Enter Serial number> ")
  val serialNumber = StdIn.readInt

  println("Enter Serial designation> ")
  val designation = StdIn.readLine()

  val serialFolder = new SerialFolder(dirpath, serialNumber, new WebArchiveSerialFileFilter)
  val serialConverter = new WebArchiveSerialFilenameConverter(designation, serialEpisodeOffset, serialNumber)
  val reorganizer = SerialFileReorganizer(serialFolder, serialConverter)

  val reorganizedFiles = reorganizer.reorganized
  reorganizedFiles.foreach(tup => println(s"${Paths.get(tup._1).getFileName}\t\t->${Paths.get(tup._2).getFileName}"))

  println("Do you want to proceed? y/N> ")
  val proceed = StdIn.readChar().toLower == 'y'

  if (proceed) {
    reorganizedFiles.foreach(tup => FileUtility.move(tup._1, tup._2))
  }
}