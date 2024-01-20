package whodarr.classic.util

import java.nio.file.{Paths, Files}
import scala.util.Try

object UtilInputOutput:
  def move(srcPath: String, dstPath: String): Try[Unit] =
    Try(Files.move(Paths.get(srcPath), Paths.get(dstPath)))
