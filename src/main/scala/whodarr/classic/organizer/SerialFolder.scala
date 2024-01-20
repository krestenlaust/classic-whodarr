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
class SerialFolder(folderPath: String, serialNumber: Int, serialFileFilter: SerialFileFilter):
  def allFiles: Seq[String] =
    allFilesUnfiltered
      .filter(p => serialFileFilter.episodeFileInSerialPredicate(p, serialNumber) && serialFileFilter.episodeFilePredicate(p))

  def allSubtitleFiles: Seq[String] =
    allFilesUnfiltered
      .filter(p => serialFileFilter.episodeFileInSerialPredicate(p, serialNumber) && serialFileFilter.episodeSubtitleFilePredicate(p))

  def allVideoFiles: Seq[String] =
    allFilesUnfiltered
      .filter(p => serialFileFilter.episodeFileInSerialPredicate(p, serialNumber) && serialFileFilter.episodeVideoFilePredicate(p))

  // TODO: Handle caught exception.
  private def allFilesUnfiltered = FileUtility.getFilePathsInFolder(folderPath).get