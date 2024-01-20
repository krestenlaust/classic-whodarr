package whodarr.classic.organizer.webarchive

import whodarr.classic.organizer.SerialFileFilter

import java.nio.file.Paths

class WebArchiveSerialFileFilter extends SerialFileFilter:
  override def episodeSubtitleFilePredicate(filePath: String): Boolean =
    filePath.endsWith(".vtt") || filePath.endsWith(".txt")

  override def episodeVideoFilePredicate(filePath: String): Boolean =
    filePath.endsWith(".mp4") || filePath.endsWith(".avi")

  override def episodeBonusFilePredicate(filePath: String): Boolean =
    getEpisodeInfoPart(filePath).endsWith("Bonus")

  override def episodeFileInSerialPredicate(filePath: String, serialNumber: Int): Boolean =
    getEpisodeInfoPart(filePath).contains("(%03d)".format(serialNumber))

  private def getFilename(filePath: String): String =
    Paths.get(filePath).getFileName.toString

  private def getEpisodeInfoPart(filePath: String): String =
    getFilename(filePath).split(" - ")(1)