package whodarr.classic.cli

import whodarr.classic.episodeinfo.webarchive.{WebArchiveSimpleEpisodeRecognizer, WebArchiveSerialFileFilter}
import whodarr.classic.organizer.webarchive.WebArchiveSerialFilenameConverter
import whodarr.classic.organizer.{SerialFileReorganizer, SerialFolderLocal}
import whodarr.classic.util.FileUtility

import java.nio.file.Paths
import scala.io.StdIn

@main
def main(): Unit = {
  println("Enter path to folder> ")
  val dirpath = StdIn.readLine()

  println("Enter Serial episode offset> ")
  val serialEpisodeOffset = StdIn.readInt

  println("Enter Story number> ")
  val storyNumber = StdIn.readInt

  val episodeRecognizer = WebArchiveSimpleEpisodeRecognizer(serialEpisodeOffset)

  val serialFolder = SerialFolderLocal(
    dirpath,
    storyNumber,
    WebArchiveSerialFileFilter(),
    episodeRecognizer
  )

  val serialConverter = WebArchiveSerialFilenameConverter(
    episodeRecognizer
  )

  val reorganizer = SerialFileReorganizer(serialFolder, serialConverter)

  val reorganizedFiles = reorganizer.reorganized
  reorganizedFiles.foreach(tup => println(s"${Paths.get(tup._1).getFileName}\t\t->${Paths.get(tup._2).getFileName}"))

  println("Do you want to proceed? y/N> ")
  val proceed = StdIn.readChar().toLower == 'y'

  if (proceed) {
    reorganizedFiles.foreach(tup => {
      println(s"Moved: ${FileUtility.move(Paths.get(tup._1), Paths.get(tup._2))}")
    })
  }
}
