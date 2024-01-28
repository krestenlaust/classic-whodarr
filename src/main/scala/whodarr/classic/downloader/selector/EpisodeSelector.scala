package whodarr.classic.downloader.selector

import whodarr.classic.episodeinfo.EpisodeId

trait EpisodeSelector:
  def selectEpisodes(episodes: Seq[EpisodeId]): Seq[EpisodeId]
