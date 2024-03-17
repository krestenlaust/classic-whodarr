package whodarr.classic.organizer.webarchive.tests

import whodarr.classic.organizer.SerialFilenameConverter

class DummyFilenameConverter(result: String) extends SerialFilenameConverter {
  def convertEpisodeFilename(filename: String): String =
    result
}
