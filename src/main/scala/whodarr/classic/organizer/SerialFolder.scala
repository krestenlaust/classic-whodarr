package whodarr.classic.organizer

trait SerialFolder:
  def listAllFiles: List[String]

  def listSubtitleFiles: List[String]

  def listVideoFiles: List[String]
