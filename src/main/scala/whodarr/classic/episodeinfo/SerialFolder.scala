package whodarr.classic.episodeinfo

trait SerialFolder:
  def allFiles: Seq[String]

  def allNonBonusFiles: Seq[String]

  def allSubtitleFiles: Seq[String]

  def allVideoFiles: Seq[String]