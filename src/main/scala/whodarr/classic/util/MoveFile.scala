package whodarr.classic.util

import os.Path

class MoveFile extends LinkFile[Path]:
  override def linkFile(src: Path, dst: Path): Boolean = FileUtility.move(src, dst)
