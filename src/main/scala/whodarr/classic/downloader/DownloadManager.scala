package whodarr.classic.downloader

import whodarr.classic.episodeinfo.{EpisodeId, EpisodeMedia}

import scala.collection.mutable
import scala.concurrent.Future

class DownloadManager:
  val queuedEpisodes: mutable.Map[EpisodeId, List[Future[Option[EpisodeMedia]]]] = mutable.Map()
  val acquirers: List[MediaAcquirer] = List()

  def queueEpisode(episodeId: EpisodeId): Unit =
    queuedEpisodes.addOne(episodeId, downloadEpisode(episodeId))

  def serializeEpisodeList =
    ???

  private def downloadEpisode(episodeId: EpisodeId): List[Future[Option[EpisodeMedia]]] =
    acquirers.map(a => a.acquireMedia(episodeId))
