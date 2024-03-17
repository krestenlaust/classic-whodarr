package whodarr.classic.organizer.webarchive.tests

import org.scalatest.flatspec.AnyFlatSpec
import whodarr.classic.episodeinfo.{EpisodeId, EpisodeMedia, EpisodeRecognizer}
import whodarr.classic.organizer.{SerialFileReorganizer, SerialFilenameSanitizer}

import java.nio.file.Paths

class SerialFileReorganizerTests extends AnyFlatSpec {
  "Serial File Reorganizer dummy" should "not rename the files" in {
    // Arrange
    val fileSep: String = System.getProperty("file.separator")
    val testPath = s"foldername${fileSep}episodename.mp4"
    val dummySerialFolder = DummySerialFolder(Seq {
      EpisodeMedia(EpisodeId(1, 0), Paths.get(testPath))
    })
    val dummyFilenameConverter = DummyFilenameConverter("episodename.mp4")

    val reorganizer: SerialFileReorganizer = SerialFileReorganizer(dummySerialFolder, dummyFilenameConverter, None)

    // Act / Assert
    assert(reorganizer.reorganized.head._2 === testPath)
  }
}