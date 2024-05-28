package whodarr.classic.util

import java.io.File
import java.nio.file.{ Files, Path, Paths }
import scala.collection.JavaConverters.*
import scala.util.Try
import os._

object FileUtility:
  def move(srcPath: os.Path, dstPath: os.Path): Boolean =
    val fileToMove = File(srcPath.toString)
    val targetFile = File(dstPath.toString)
    fileToMove.renameTo(targetFile)

  def getFilePathsInFolder(folderPath: os.Path): Try[IndexedSeq[os.Path]] =
    Try(os.list(folderPath))
