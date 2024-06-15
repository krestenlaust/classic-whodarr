package whodarr.classic.organizer.webarchive

import os.Path
import whodarr.classic.episodeinfo.SerialFolderLocal
import whodarr.classic.episodeinfo.webarchive.{ EpisodeRecognizerImplSimple, SerialFileFilterImpl }
import whodarr.classic.organizer.SerialFileReorganizer

case object StoryReorganizer:
  def reorganizeStory(src: Path, episodeOffset: Int, storyNumber: Int): SerialFileReorganizer =
    val episodeRecognizer = EpisodeRecognizerImplSimple(episodeOffset)

    val serialFolder = SerialFolderLocal(
      src,
      storyNumber,
      SerialFileFilterImpl(),
      episodeRecognizer
    )

    val serialConverter = SerialFilenameConverterImpl(
      episodeRecognizer
    )

    SerialFileReorganizer(serialFolder, serialConverter)
