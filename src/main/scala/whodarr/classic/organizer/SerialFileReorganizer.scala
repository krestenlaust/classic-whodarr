package whodarr.classic.organizer

import whodarr.classic.episodeinfo.SerialFolder

class SerialFileReorganizer(serialFolder: SerialFolder, serialFilenameConverter: SerialFilenameConverter):
  /**
   * @return current file paths mapped to new paths.
   */
  def reorganized: Map[String, String] =
    serialFolder.allNonBonusFiles.map(path => path -> serialFilenameConverter.convertEpisodeName(path)).toMap