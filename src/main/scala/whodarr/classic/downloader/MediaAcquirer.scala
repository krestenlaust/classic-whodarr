package whodarr.classic.downloader

import whodarr.classic.episodeinfo.{EpisodeId, EpisodeMedia}

import scala.concurrent.Future

trait MediaAcquirer:
  def acquireMedia(episodeId: EpisodeId): Future[Option[EpisodeMedia]]
