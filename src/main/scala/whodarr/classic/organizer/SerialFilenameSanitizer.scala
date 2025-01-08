package whodarr.classic.organizer

trait SerialFilenameSanitizer:
  def sanitizeFilename(filename: String): String
