package whodarr.classic.organizer

import whodarr.classic.episodeinfo.SerialFolder

import java.nio.file.Path

class SerialFileReorganizer(serialFolder: SerialFolder, serialFilenameConverter: SerialFilenameConverter, dstPath: Option[Path]):
  /**
   * @return current file paths mapped to new paths.
   */
  def reorganized: Map[String, String] =
    serialFolder.allNonBonusFiles
      .map(
        media =>
          media.path.toString -> dstPath.getOrElse(media.path.getParent).resolveSibling(
            serialFilenameConverter.convertEpisodeFilename(media.path.getFileName.toString)
          ).toString
      )
      .toMap