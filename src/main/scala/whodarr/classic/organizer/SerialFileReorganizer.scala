package whodarr.classic.organizer

import whodarr.classic.episodeinfo.SerialFolder

import os.Path

class SerialFileReorganizer(
    serialFolder: SerialFolder,
    serialFilenameConverter: SerialFilenameConverter,
    dstPath: Option[Path]
):
  /** @return
    *   current file paths mapped to new paths.
    */
  def reorganized: Map[Path, Path] =
    serialFolder.allNonBonusFiles
      .map(media =>
        media.path ->
          (dstPath.getOrElse(media.path / os.up) /
            serialFilenameConverter
              .convertEpisodeFilename(media.path.last))
      )
      .toMap
