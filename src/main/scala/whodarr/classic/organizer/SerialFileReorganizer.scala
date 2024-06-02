package whodarr.classic.organizer

import os.Path
import whodarr.classic.episodeinfo.SerialFolder

class SerialFileReorganizer(
    serialFolder: SerialFolder,
    serialFilenameConverter: SerialFilenameConverter
):
  /** @return
    *   current file paths mapped to new paths.
    */
  def reorganized(dstPath: Option[Path]): Map[Path, Path] =
    serialFolder.allNonBonusFiles
      .map(media =>
        media.path ->
          (dstPath.getOrElse(media.path / os.up) /
            serialFilenameConverter
              .convertEpisodeFilename(media.path.last))
      )
      .toMap
