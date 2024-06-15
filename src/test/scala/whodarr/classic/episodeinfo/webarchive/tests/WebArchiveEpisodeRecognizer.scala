package whodarr.classic.episodeinfo.webarchive.tests

import org.scalatest.flatspec.AnyFlatSpec
import whodarr.classic.episodeinfo.{ EpisodeId, EpisodeRecognizer }
import whodarr.classic.episodeinfo.webarchive.WebArchiveEpisodeRecognizerSimple

class WebArchiveEpisodeRecognizer extends AnyFlatSpec {
  "Simple Recognizer" should "recognize simple episode name" in {
    // Arrange
    val recognizer: EpisodeRecognizer =
      WebArchiveEpisodeRecognizerSimple(10) // Offset by 10 episodes

    // Act / Assert
    assert(
      recognizer
        .detectFromPath("Doctor Who - S08E03 (057) - The Claws of Axos (1).mp4")
        .get === EpisodeId(8, 11)
    )

    // With extra in filename
    assert(
      recognizer
        .detectFromPath("Doctor Who - S08E03 (057) - The Claws of Axos (2).autogenerated.vtt")
        .get === EpisodeId(8, 12)
    )
  }

  "Simple Recognizer" should "recognize episode names in long paths" in {
    val recognizer: EpisodeRecognizer = WebArchiveEpisodeRecognizerSimple(0)
    val fileSep: String               = System.getProperty("file.separator")

    val testPath =
      s"""${fileSep}Doctor Who${fileSep}Doctor Who - S10E01 (065) - The Three Doctors - Parts 1-4${fileSep}Doctor Who - S10E01 (065) - The Three Doctors (1).avi"""

    assert(
      recognizer.detectFromPath(testPath).get === EpisodeId(10, 1)
    )
  }
}
