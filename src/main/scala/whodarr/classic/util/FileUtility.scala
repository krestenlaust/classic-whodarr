package whodarr.classic.util

import os.Path

import java.io.File
import scala.util.Try

object FileUtility:
  def move(srcPath: Path, dstPath: Path): Boolean =
    val fileToMove = File(srcPath.toString)
    val targetFile = File(dstPath.toString)
    fileToMove.renameTo(targetFile)

  def getFilePathsInFolder(folderPath: Path): Try[IndexedSeq[Path]] =
    Try(os.list(folderPath))

  def massFileOperation[A](fileMappings: Map[A, A], fileLinker: FileOperation[A]): Unit =
    fileMappings.foreach(mapping => fileLinker.operation(mapping._1, mapping._2))
