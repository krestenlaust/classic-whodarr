package whodarr.classic.episodeinfo.webarchive

import whodarr.classic.episodeinfo.{ Recognizer, SeasonId }

class SeasonRecognizerImpl extends Recognizer[SeasonId]:
  override def detectFromPath(path: String): Option[SeasonId] = ???
