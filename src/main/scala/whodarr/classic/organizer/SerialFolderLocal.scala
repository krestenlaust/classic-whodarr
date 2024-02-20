package whodarr.classic.organizer

import whodarr.classic.episodeinfo.{EpisodeMedia, EpisodeRecognizer, SerialFileFilter, SerialFolder}
import whodarr.classic.util.FileUtility

import java.nio.file.Path

/**
 * Represents a virtual folder of files related to a particular serial.
 * Filters files for a specific serial, so multiple serials can still be in the same folder.
 *
 * @param folderPath       The path to the folder of serial episodes.
 * @param storyNumber     The story number of a serial.
 * @param serialFileFilter The filter to use to identify file types.
 */
class SerialFolderLocal(folderPath: String, storyNumber: Int, serialFileFilter: SerialFileFilter, episodeRecognizer: EpisodeRecognizer) extends SerialFolder:
  def allFiles: Seq[EpisodeMedia] =
    allFilesInSerial(_ => true)

  private def allFilesInSerial(predicate: String => Boolean): Seq[EpisodeMedia] =
    allFilesUnfiltered.filter(p =>
      serialFileFilter.episodeFileInSerialPredicate(p.toString, serialNumber) &&
      serialFileFilter.episodeFilePredicate(p.toString) &&
      predicate(p.toString)
    ).flatMap { path =>
      episodeRecognizer.detectFromPath(path.toString) match {
        case Some(episodeId) => Some(EpisodeMedia(episodeId, path))
        case None => None
      }
    }

  // TODO: Handle caught exception.
  private def allFilesUnfiltered: Seq[Path] =
    FileUtility.getFilePathsInFolder(folderPath).get

  def allNonBonusFiles: Seq[EpisodeMedia] =
    allFilesInSerial(p => !serialFileFilter.episodeBonusFilePredicate(p))

  def allSubtitleFiles: Seq[EpisodeMedia] =
    allFilesInSerial(serialFileFilter.episodeSubtitleFilePredicate)

  def allVideoFiles: Seq[EpisodeMedia] =
    allFilesInSerial(serialFileFilter.episodeVideoFilePredicate)
