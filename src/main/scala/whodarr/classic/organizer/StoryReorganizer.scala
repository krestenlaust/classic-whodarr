package whodarr.classic.organizer

import os.Path
import whodarr.classic.episodeinfo.webarchive.{ WebArchiveSerialFileFilter, WebArchiveEpisodeRecognizerSimple }
import whodarr.classic.organizer.webarchive.WebArchiveSerialFilenameConverter

case object StoryReorganizer:
  def reorganizeStory(src: Path, episodeOffset: Int, storyNumber: Int): SerialFileReorganizer =
    val episodeRecognizer = WebArchiveEpisodeRecognizerSimple(episodeOffset)

    val serialFolder = SerialFolderLocal(
      src,
      storyNumber,
      WebArchiveSerialFileFilter(),
      episodeRecognizer
    )

    val serialConverter = WebArchiveSerialFilenameConverter(
      episodeRecognizer
    )

    SerialFileReorganizer(serialFolder, serialConverter)
