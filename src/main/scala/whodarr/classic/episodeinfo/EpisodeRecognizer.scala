package whodarr.classic.episodeinfo

trait EpisodeRecognizer:
  def detectFromPath(filePath: String) : EpisodeId
