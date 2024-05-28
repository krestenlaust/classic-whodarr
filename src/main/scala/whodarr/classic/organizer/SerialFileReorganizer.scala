package whodarr.classic.organizer

import whodarr.classic.episodeinfo.SerialFolder

import os._

class SerialFileReorganizer(
    serialFolder: SerialFolder,
    serialFilenameConverter: SerialFilenameConverter,
    dstPath: Option[os.Path]
):
  /** @return
    *   current file paths mapped to new paths.
    */
  def reorganized: Map[String, String] =
    serialFolder.allNonBonusFiles
      .map(media =>
        media.path.toString ->
          (dstPath.getOrElse(media.path / os.up) /
            serialFilenameConverter
              .convertEpisodeFilename(media.path.last)).toString
      )
      .toMap
