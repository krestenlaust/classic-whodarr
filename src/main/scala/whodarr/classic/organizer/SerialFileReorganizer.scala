package whodarr.classic.organizer

class SerialFileReorganizer(serialFolder: SerialFolder, serialFilenameConverter: SerialFilenameConverter):
  /**
   * @return current file paths mapped to new paths.
   */
  def reorganized: Map[String, String] =
    serialFolder.listAllFiles.map(path => path -> serialFilenameConverter.convertEpisodeName(path)).toMap