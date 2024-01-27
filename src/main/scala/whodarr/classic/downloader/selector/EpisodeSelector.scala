package whodarr.classic.downloader.selector

import whodarr.classic.types.EpisodeId

trait EpisodeSelector:
  def episodes: Seq[EpisodeId]
