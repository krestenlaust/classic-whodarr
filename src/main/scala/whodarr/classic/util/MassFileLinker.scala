package whodarr.classic.util

trait MassFileLinker[A]:
  def link(fileMappings: Map[A, A], fileLinker: LinkFile[A]): Unit
