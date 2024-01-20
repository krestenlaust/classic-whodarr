package whodarr.classic.util

import java.nio.file.{Paths, Files}
import scala.collection.JavaConverters.*
import scala.util.Try

object FileUtility:
  def move(srcPath: String, dstPath: String): Try[Unit] =
    Try(Files.move(Paths.get(srcPath), Paths.get(dstPath)))

  def getFilePathsInFolder(folderPath: String): Try[Seq[String]] =
    Try(Files.list(Paths.get(folderPath)).iterator().asScala.toSeq.map(p => p.toString))