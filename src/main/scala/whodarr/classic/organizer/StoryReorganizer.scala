package whodarr.classic.organizer

import os.Path
import whodarr.classic.episodeinfo.webarchive.{ WebArchiveSerialFileFilter, WebArchiveSimpleEpisodeRecognizer }
import whodarr.classic.organizer.webarchive.WebArchiveSerialFilenameConverter

case object StoryReorganizer:
  def reorganizeStory(src: Path, episodeOffset: Int, storyNumber: Int): SerialFileReorganizer =
    val episodeRecognizer = WebArchiveSimpleEpisodeRecognizer(episodeOffset)

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
