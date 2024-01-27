package whodarr.classic.util

import java.io.File
import java.nio.file.{Files, Paths}
import scala.collection.JavaConverters.*
import scala.util.Try

object FileUtility:
  def move(srcPath: String, dstPath: String): Boolean =
    val fileToMove = File(srcPath)
    val targetFile = File(dstPath)
    fileToMove.renameTo(targetFile)

  def getFilePathsInFolder(folderPath: String): Try[Seq[String]] =
    Try(Files.list(Paths.get(folderPath)).iterator().asScala.toSeq.map(p => p.toString))