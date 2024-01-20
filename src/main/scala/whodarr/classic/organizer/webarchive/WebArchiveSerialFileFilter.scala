package whodarr.classic.organizer.webarchive

import whodarr.classic.organizer.SerialFileFilter

class WebArchiveSerialFileFilter extends SerialFileFilter:
  override def episodeSubtitleFilePredicate(filePath: String): Boolean =
    filePath.endsWith(".vtt") || filePath.endsWith(".txt")

  override def episodeVideoFilePredicate(filePath: String): Boolean =
    filePath.endsWith(".mp4") || filePath.endsWith(".avi")

  override def episodeBonusFilePredicate(filePath: String): Boolean =
    ???

  override def episodeFileInSerialPredicate(filePath: String, serialNumber: Int): Boolean =
    ???
