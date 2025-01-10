package whodarr.classic.episodeinfo.webarchive

import whodarr.classic.episodeinfo.{ Recognizer, SerialId }

class SerialRecognizerImpl extends Recognizer[SerialId]:
  override def detectFromPath(path: String): Option[SerialId] =
    ???
