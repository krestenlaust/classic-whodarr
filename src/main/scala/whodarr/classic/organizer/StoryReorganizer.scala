package whodarr.classic.organizer

import os.Path
import whodarr.classic.episodeinfo.webarchive.{ EpisodeRecognizerImplSimple, SerialFileFilterImpl }
import whodarr.classic.organizer.webarchive.WebArchiveSerialFilenameConverter

case object StoryReorganizer:
  def reorganizeStory(src: Path, episodeOffset: Int, storyNumber: Int): SerialFileReorganizer =
    val episodeRecognizer = EpisodeRecognizerImplSimple(episodeOffset)

    val serialFolder = SerialFolderLocal(
      src,
      storyNumber,
      SerialFileFilterImpl(),
      episodeRecognizer
    )

    val serialConverter = WebArchiveSerialFilenameConverter(
      episodeRecognizer
    )

    SerialFileReorganizer(serialFolder, serialConverter)
