package whodarr.classic.episodeinfo

/** Represents a collection of episodes' media in a serial. E.g. A folder of subtitles for a serial, or a folder of video
  * tracks, for a serial.
  */
trait SerialFolder:
  def allFiles: Seq[EpisodeMedia]

  def allNonBonusFiles: Seq[EpisodeMedia]

  def allSubtitleFiles: Seq[EpisodeMedia]

  def allVideoFiles: Seq[EpisodeMedia]
