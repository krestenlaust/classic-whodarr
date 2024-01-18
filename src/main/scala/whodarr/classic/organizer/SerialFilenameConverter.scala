package whodarr.classic.organizer

trait SerialFilenameConverter:
  def convertEpisodeName(name: String): String
