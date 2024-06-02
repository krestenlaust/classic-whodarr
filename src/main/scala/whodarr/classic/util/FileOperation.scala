package whodarr.classic.util

/** Represents a file operation involving two paths as parameters.
  * Moves, links or similar, to files.
  * @tparam A Path-type that identifies a file.
  */
trait FileOperation[A]:
  def operation(srcPath: A, dstPath: A): Boolean
