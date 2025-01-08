package whodarr.classic.util

import os.Path

/** Move a file
  */
class MoveFileOperation extends FileOperation[Path]:
  /** Renames a file's path to a new path.
    * @param srcPath The source path to the file
    * @param dstPath The destination path with the new file name
    * @return Whether the operation was successful
    */
  override def operation(srcPath: Path, dstPath: Path): Boolean = FileUtility.move(srcPath, dstPath)
