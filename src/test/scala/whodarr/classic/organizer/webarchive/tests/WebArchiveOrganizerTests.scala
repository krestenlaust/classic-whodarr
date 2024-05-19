package whodarr.classic.organizer.webarchive.tests

import org.scalatest.flatspec.AnyFlatSpec
import whodarr.classic.episodeinfo.EpisodeRecognizer
import whodarr.classic.episodeinfo.webarchive.WebArchiveSimpleEpisodeRecognizer
import whodarr.classic.organizer.{ SerialFilenameConverter, SerialFilenameSanitizer }
import whodarr.classic.organizer.webarchive.WebArchiveSerialFilenameConverter

class WebArchiveOrganizerTests extends AnyFlatSpec {
  "WebArchive Sanitizer" should "remove .autogenerated from file names" in {
    // Arrange
    // Note: For the time being, these parameters aren't necessary for the sanitizing functionality.
    val recognizer: EpisodeRecognizer = WebArchiveSimpleEpisodeRecognizer(10)
    val sanitizer: SerialFilenameSanitizer =
      WebArchiveSerialFilenameConverter(recognizer)

    // Act / Assert
    assert(
      sanitizer.sanitizeFilename(
        "Doctor Who - S08E03 (057) - The Claws of Axos (2).autogenerated.vtt"
      ) ===
        "Doctor Who - S08E03 (057) - The Claws of Axos (2).vtt"
    )
    assert(
      sanitizer.sanitizeFilename(
        "Doctor Who - S08E03 (057) - The Claws of Axos (3).autogenerated.txt"
      ) ===
        "Doctor Who - S08E03 (057) - The Claws of Axos (3).txt"
    )
  }

  "WebArchive Converter" should "convert episode names properly" in {
    // Arrange
    val recognizer: EpisodeRecognizer = WebArchiveSimpleEpisodeRecognizer(10)
    val converter: SerialFilenameConverter =
      WebArchiveSerialFilenameConverter(recognizer)

    // Act / Assert
    assert(
      converter.convertEpisodeFilename(
        "Doctor Who - S08E03 (057) - The Claws of Axos (2).autogenerated.vtt"
      ) ===
        "Doctor Who - S08E12 (057) - The Claws of Axos (2).vtt"
    )
    assert(
      converter.convertEpisodeFilename(
        "Doctor Who - S08E03 (057) - The Claws of Axos (3).autogenerated.txt"
      ) ===
        "Doctor Who - S08E13 (057) - The Claws of Axos (3).txt"
    )
  }
}
