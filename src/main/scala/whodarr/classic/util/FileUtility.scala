package whodarr.classic.util

import java.io.File
import scala.util.Try
import os.Path

object FileUtility:
  def move(srcPath: Path, dstPath: Path): Boolean =
    val fileToMove = File(srcPath.toString)
    val targetFile = File(dstPath.toString)
    fileToMove.renameTo(targetFile)

  def getFilePathsInFolder(folderPath: Path): Try[IndexedSeq[Path]] =
    Try(os.list(folderPath))
