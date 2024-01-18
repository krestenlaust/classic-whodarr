package whodarr.classic.organizer.webarchive

import whodarr.classic.organizer.SerialFolder

class WebArchiveSerialFolder(val folderPath: String) extends SerialFolder:
  override def listAllFiles: List[String] = ???

  override def listSubtitleFiles: List[String] = ???

  override def listVideoFiles: List[String] = ???

