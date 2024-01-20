package whodarr.classic.organizer

import whodarr.classic.util.FileUtility

/**
 * Represents a virtual folder of files related to a particular serial.
 * Filters files for a specific serial, so multiple serials can still be in the same folder.
 *
 * @param folderPath The path to the folder of serial episodes.
 * @param serialNumber The story number of a serial.
 * @param serialFileFilter The filter to use to identify file types.
 */
class SerialFolderImpl(folderPath: String, serialNumber: Int, serialFileFilter: SerialFileFilter):
  def allFiles: Seq[String] =
    allFilesInSerial(_ => true)

  def allNonBonusFiles: Seq[String] =
    allFilesInSerial(p => !serialFileFilter.episodeBonusFilePredicate(p))

  def allSubtitleFiles: Seq[String] =
    allFilesInSerial(serialFileFilter.episodeSubtitleFilePredicate)

  def allVideoFiles: Seq[String] =
    allFilesInSerial(serialFileFilter.episodeVideoFilePredicate)

  private def allFilesInSerial(predicate: String => Boolean): Seq[String] =
    allFilesUnfiltered.filter(p => serialFileFilter.episodeFileInSerialPredicate(p, serialNumber) &&
      serialFileFilter.episodeFilePredicate(p) &&
      predicate(p))

  // TODO: Handle caught exception.
  private def allFilesUnfiltered = FileUtility.getFilePathsInFolder(folderPath).get