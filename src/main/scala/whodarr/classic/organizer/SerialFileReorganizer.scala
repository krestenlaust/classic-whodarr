package whodarr.classic.organizer

import whodarr.classic.episodeinfo.SerialFolder

class SerialFileReorganizer(serialFolder: SerialFolder, serialFilenameConverter: SerialFilenameConverter):
  /**
   * @return current file paths mapped to new paths.
   */
  def reorganized: Map[String, String] =
    serialFolder.allNonBonusFiles
      .map(
        media =>
          media.path.toString -> media.path.getParent.resolveSibling(
            serialFilenameConverter.convertEpisodeName(media.path.getFileName.toString)
          ).toString
      )
      .toMap