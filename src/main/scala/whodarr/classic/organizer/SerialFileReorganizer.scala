package whodarr.classic.organizer

import os.Path
import whodarr.classic.episodeinfo.SerialFolder
import whodarr.classic.util.LinkFile

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

  def reorganize(fileLinker: LinkFile[Path]): Unit =
    reorganized.foreach(mapping => fileLinker.linkFile(mapping._1, mapping._2))
