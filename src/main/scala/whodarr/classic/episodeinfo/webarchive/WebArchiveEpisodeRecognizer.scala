package whodarr.classic.episodeinfo.webarchive

import whodarr.classic.episodeinfo.{EpisodeId, EpisodeRecognizer}

class WebArchiveEpisodeRecognizer extends EpisodeRecognizer:
  override def detectFromPath(filePath: String) : Option[EpisodeId] =
    // TODO: Find a way to lookup episodes, to find serial-episode offset.
    ???
