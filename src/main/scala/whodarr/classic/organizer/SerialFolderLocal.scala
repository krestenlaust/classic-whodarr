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
    allMediaFiltered(_ => true)

  def allNonBonusFiles: Seq[EpisodeMedia] =
    allMediaFiltered(p => !serialFileFilter.episodeBonusFilePredicate(p))

  def allSubtitleFiles: Seq[EpisodeMedia] =
    allMediaFiltered(serialFileFilter.episodeSubtitleFilePredicate)

  def allVideoFiles: Seq[EpisodeMedia] =
    allMediaFiltered(serialFileFilter.episodeVideoFilePredicate)

  private def filesFiltered(files: Seq[Path], predicate: String => Boolean): Seq[Path] =
    files.filter(p =>
      serialFileFilter.episodeFileInSerialPredicate(p.toString, storyNumber) &&
        serialFileFilter.episodeFilePredicate(p.toString) &&
        predicate(p.toString)
    )

  private def filesToEpisodeMedia(files: Seq[Path]): Seq[EpisodeMedia] =
    files.flatMap { path =>
      episodeRecognizer.detectFromPath(path.toString) match
        case Some(episodeId) => Some(EpisodeMedia(episodeId, path))
        case None => None
    }

  private def allMediaFiltered(predicate: String => Boolean): Seq[EpisodeMedia] =
    filesToEpisodeMedia(filesFiltered(allFilesUnfiltered, predicate))

  // TODO: Handle caught exception.
  private def allFilesUnfiltered: Seq[Path] =
    FileUtility.getFilePathsInFolder(folderPath).get