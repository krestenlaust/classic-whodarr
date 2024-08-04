package whodarr.classic.episodeinfo

import os.Path

class SeasonFolderLocal(
    folderPath: Path,
    episodeRecognizer: Recognizer[EpisodeId]
) extends SeasonFolder:
  override def serials: Seq[SerialFolderLocal] =
    ???
