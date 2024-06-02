package whodarr.classic.util

trait LinkFile[A]:
  def linkFile(src: A, dst: A): Boolean
