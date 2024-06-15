package whodarr.classic.episodeinfo

import os.Path
import whodarr.classic.util.FileUtility

/** Represents a virtual folder of files related to a particular serial. Filters files for a specific serial, so multiple
  * serials can still be in the same folder.
  *
  * @param folderPath
  *   The path to the folder of serial episodes.
  * @param storyNumber
  *   The story number of a serial.
  * @param episodeFileFilter
  *   The filter to use to identify file types.
  */
class SerialFolderLocal(
    folderPath: Path,
    storyNumber: Int,
    episodeFileFilter: EpisodeFileFilter,
    episodeRecognizer: Recognizer[EpisodeId]
) extends SerialFolder:
  def allFiles: Seq[EpisodeMedia] =
    allMediaFiltered(_ => true)

  def allNonBonusFiles: Seq[EpisodeMedia] =
    allMediaFiltered(p => !episodeFileFilter.episodeBonusFilePredicate(p))

  def allSubtitleFiles: Seq[EpisodeMedia] =
    allMediaFiltered(episodeFileFilter.episodeSubtitleFilePredicate)

  def allVideoFiles: Seq[EpisodeMedia] =
    allMediaFiltered(episodeFileFilter.episodeVideoFilePredicate)

  private def filesFiltered(
      files: Seq[Path],
      predicate: Path => Boolean
  ): Seq[Path] =
    files.filter(p =>
      episodeFileFilter.episodeFileInSerialPredicate(p, storyNumber) &&
        episodeFileFilter.episodeFilePredicate(p) &&
        predicate(p)
    )

  private def filesToEpisodeMedia(files: Seq[Path]): Seq[EpisodeMedia] =
    files.flatMap { path =>
      episodeRecognizer.detectFromPath(path.toString) match
        case Some(episodeId) => Some(EpisodeMedia(episodeId, path))
        case None            => None
    }

  private def allMediaFiltered(
      predicate: Path => Boolean
  ): Seq[EpisodeMedia] =
    filesToEpisodeMedia(filesFiltered(allFilesUnfiltered, predicate))

  // TODO: Handle caught exception.
  private def allFilesUnfiltered: Seq[os.Path] =
    FileUtility.getFilePathsInFolder(folderPath).get
