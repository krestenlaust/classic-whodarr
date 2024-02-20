package whodarr.classic.episodeinfo.webarchive

import whodarr.classic.episodeinfo.{EpisodeId, EpisodeMedia, EpisodeRecognizer}

/**
 * A simple episode recognizer. Depends on getting episode offset in the season.
 * @param episodeOffset The offset (0-indexed) of the first episode in the serial.
 */
class WebArchiveSimpleEpisodeRecognizer(episodeOffset: Int) extends EpisodeRecognizer:
  override def detectFromPath(filePath: String): EpisodeId =
    ???
  override def detectFromPath(filePath: String): Option[EpisodeId] =
