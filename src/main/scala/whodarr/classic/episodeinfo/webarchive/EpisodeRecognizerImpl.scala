package whodarr.classic.episodeinfo.webarchive

import whodarr.classic.episodeinfo.{ EpisodeId, Recognizer }

class EpisodeRecognizerImpl extends Recognizer[EpisodeId]:
  override def detectFromPath(filePath: String): Option[EpisodeId] =
    // TODO: Find a way to lookup episodes, to find serial-episode offset.
    ???
