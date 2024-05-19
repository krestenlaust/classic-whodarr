package whodarr.classic.util

import java.io.File
import java.nio.file.{ Files, Path, Paths }
import scala.collection.JavaConverters.*
import scala.util.Try

object FileUtility:
  def move(srcPath: Path, dstPath: Path): Boolean =
    val fileToMove = File(srcPath.toString)
    val targetFile = File(dstPath.toString)
    fileToMove.renameTo(targetFile)

  def getFilePathsInFolder(folderPath: String): Try[Seq[Path]] =
    Try(
      Files
        .list(Paths.get(folderPath))
        .iterator()
        .asScala
        .toSeq
    )
