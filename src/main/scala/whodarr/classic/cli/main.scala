package whodarr.classic.cli

import os.Path
import whodarr.classic.episodeinfo.webarchive.{ WebArchiveSerialFileFilter, WebArchiveSimpleEpisodeRecognizer }
import whodarr.classic.organizer.webarchive.WebArchiveSerialFilenameConverter
import whodarr.classic.organizer.{ SerialFileReorganizer, SerialFolderLocal }
import whodarr.classic.util.MoveFileOperation

import scala.io.StdIn

@main
def main(): Unit = {
  println("Enter path to folder> ")
  val dirpath = os.Path(StdIn.readLine(), os.pwd)

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

  val reorganizedFiles = reorganizer.reorganized(None)
  reorganizedFiles.foreach(tup =>
    println(
      s"${tup._1.last}\t\t->${tup._2.last}"
    )
  )

  println("Do you want to proceed? y/N> ")
  val proceed = StdIn.readChar().toLower == 'y'

  if (proceed) {
    val fileMover = MoveFileOperation()
    reorganizedFiles.foreach { tup =>
      println(
        s"Moved: ${fileMover.operation(tup._1, tup._2)}"
      )
    }
  }
}
