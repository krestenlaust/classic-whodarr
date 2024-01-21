package whodarr.classic.downloader

import java.nio.file.Path
import scala.concurrent.Future

trait EpisodeRetriever:
  def retrieveEpisode(season: Int, episodeNumber: Int): Future[Path]

  def retrieveSerial(season: Int, serialNumber: Int): Future[Path]

  def retrieveSeason(season: Int, serialNumber: Int): Future[Path]
