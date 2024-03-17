package whodarr.classic.organizer.webarchive.tests

import whodarr.classic.episodeinfo.{EpisodeId, EpisodeRecognizer}

class DummyEpisodeRecognizer(result: Option[EpisodeId]) extends EpisodeRecognizer:
  def detectFromPath(filePath: String) : Option[EpisodeId] =
    result
