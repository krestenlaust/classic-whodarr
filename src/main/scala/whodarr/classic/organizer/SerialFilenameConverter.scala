package whodarr.classic.organizer

trait SerialFilenameConverter:
  def convertEpisodeFilename(filename: String): String
