package whodarr.classic.episodeinfo.webarchive

import whodarr.classic.episodeinfo.{EpisodeId, EpisodeMedia, EpisodeRecognizer}

import java.nio.file.Paths

/**
 * A simple episode recognizer. Depends on getting episode offset in the season.
 * @param episodeOffset The offset (0-indexed) of the first episode in the serial.
 */
class WebArchiveSimpleEpisodeRecognizer(episodeOffset: Int) extends EpisodeRecognizer:
  override def detectFromPath(filePath: String): Option[EpisodeId] =
    Paths.get(filePath).getFileName.toString
      .split(" - ") match {
      case Array(_, serialDesignation, episodeName) =>
        for {
          season <- serialDesignation.split(" ")(0).substring(1, 3).toIntOption
          episode <- episodeName.substring(
            episodeName.lastIndexOf("(") + 1,
            episodeName.lastIndexOf(")")).toIntOption
        } yield EpisodeId(season, episode + episodeOffset)
      case _ => None
    }
