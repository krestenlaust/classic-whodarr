package whodarr.classic.util

class SimpleMassFileLinker[A] extends MassFileLinker[A]:
  def link(fileMappings: Map[A, A], fileLinker: LinkFile[A]): Unit =
    fileMappings.foreach(mapping => fileLinker.linkFile(mapping._1, mapping._2))
