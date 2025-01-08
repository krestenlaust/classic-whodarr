package whodarr.classic.downloader.selector

import whodarr.classic.episodeinfo.EpisodeId

class LookAheadEpisodeSelector extends EpisodeSelector:
  override def selectEpisodes(episodes: Seq[EpisodeId]): Seq[EpisodeId] = ???
