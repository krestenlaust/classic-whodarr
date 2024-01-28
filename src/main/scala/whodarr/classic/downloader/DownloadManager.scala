package whodarr.classic.downloader

import whodarr.classic.episodeinfo.{EpisodeId, EpisodeMedia}

import scala.collection.mutable
import scala.concurrent.Future

class DownloadManager:
  val queuedEpisodes: mutable.HashSet[EpisodeId] = mutable.HashSet()
  val acquirers: List[MediaAcquirer] = List()

  def downloadEpisode(episodeId: EpisodeId): List[Future[Option[EpisodeMedia]]] =
    queuedEpisodes.add(episodeId)
    acquirers.map(a => a.acquireMedia(episodeId))