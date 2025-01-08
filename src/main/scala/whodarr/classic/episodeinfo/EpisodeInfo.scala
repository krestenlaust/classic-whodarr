package whodarr.classic.episodeinfo

import scala.concurrent.Future

trait EpisodeInfo:
  def serialFromEpisode(episodeId: EpisodeId): Future[SerialId]

  def episodesFromSerial(serialId: SerialId): Future[Seq[EpisodeId]]
