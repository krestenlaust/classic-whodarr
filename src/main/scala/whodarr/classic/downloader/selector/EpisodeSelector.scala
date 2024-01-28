package whodarr.classic.downloader.selector

import whodarr.classic.types.EpisodeId

trait EpisodeSelector:
  def selectEpisodes(episodes: Seq[EpisodeId]): Seq[EpisodeId]
