package whodarr.classic.organizer.webarchive.tests

import whodarr.classic.episodeinfo.{EpisodeMedia, SerialFolder}

class DummySerialFolder(result: Seq[EpisodeMedia]) extends SerialFolder {
  def allFiles: Seq[EpisodeMedia] = result

  def allNonBonusFiles: Seq[EpisodeMedia] = result

  def allSubtitleFiles: Seq[EpisodeMedia] = result

  def allVideoFiles: Seq[EpisodeMedia] = result
}
