package whodarr.classic.episodeinfo.webarchive

import whodarr.classic.episodeinfo.SerialFileFilter
import os.Path

class WebArchiveSerialFileFilter extends SerialFileFilter:
  override def episodeSubtitleFilePredicate(filePath: Path): Boolean =
    getFileExtension(filePath).exists(ext => ext.equalsIgnoreCase("vtt") || ext.equalsIgnoreCase("txt"))

  override def episodeVideoFilePredicate(filePath: Path): Boolean =
    getFileExtension(filePath).exists(ext => ext.equalsIgnoreCase("mp4") || ext.equalsIgnoreCase("avi"))

  override def episodeBonusFilePredicate(filePath: Path): Boolean =
    getEpisodeInfoPart(filePath).endsWith("Bonus")

  override def episodeFileInSerialPredicate(
      filePath: Path,
      serialNumber: Int
  ): Boolean =
    getEpisodeInfoPart(filePath).contains("(%03d)".format(serialNumber))

  private def getEpisodeInfoPart(filePath: Path): String =
    getFilename(filePath).split(" - ")(1)

  private def getFilename(filePath: Path): String =
    filePath.last

  private def getFileExtension(filePath: Path): Option[String] =
    filePath.lastOpt
      .flatMap(p => p.split('.').lastOption)
