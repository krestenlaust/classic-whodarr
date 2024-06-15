package whodarr.classic.organizer

import os.Path
import whodarr.classic.episodeinfo.SerialFolderLocal
import whodarr.classic.episodeinfo.webarchive.{ EpisodeRecognizerImplSimple, SerialFileFilterImpl }
import whodarr.classic.organizer.webarchive.SerialFilenameConverterImpl

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
