package whodarr.classic.downloader.qbittorrent

import whodarr.classic.episodeinfo.{EpisodeMedia, SerialFolder}

class SerialFolderTorrent extends SerialFolder:
  override def allFiles: Seq[EpisodeMedia] =
    ???

  override def allNonBonusFiles: Seq[EpisodeMedia] =
    ???

  override def allSubtitleFiles: Seq[EpisodeMedia] =
    ???

  override def allVideoFiles: Seq[EpisodeMedia] =
    ???
