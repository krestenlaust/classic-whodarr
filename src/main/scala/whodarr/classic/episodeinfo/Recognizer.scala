package whodarr.classic.episodeinfo

trait Recognizer[A]:
  def detectFromPath(path: String): Option[A]
