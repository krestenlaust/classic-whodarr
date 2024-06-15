package whodarr.classic.episodeinfo.webarchive

import os.Path
import whodarr.classic.episodeinfo.{ EpisodeId, Recognizer, SeasonId }

/** A simple episode recognizer. Depends on getting episode offset in the season.
  * @param episodeOffset
  *   The offset (0-indexed) of the first episode in the serial.
  */
class WebArchiveEpisodeRecognizerSimple(episodeOffset: Int) extends Recognizer[EpisodeId]:
  override def detectFromPath(filePath: String): Option[EpisodeId] =
    Path(filePath, os.pwd).last
      .split(" - ") match {
      case Array(_, serialDesignation, episodeName) =>
        for {
          season <- serialDesignation.split(" ")(0).substring(1, 3).toIntOption
          episode <- episodeName
            .substring(
              episodeName.lastIndexOf("(") + 1,
              episodeName.lastIndexOf(")")
            )
            .toIntOption
        } yield EpisodeId(SeasonId(season), episode + episodeOffset)
      case _ => None
    }
