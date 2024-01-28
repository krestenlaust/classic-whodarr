package whodarr.classic.organizer

/**
 * Provides predicates to identify what the files in a serial folder are.
 */
trait SerialFileFilter:
  /**
   * Whether the file is a subtitle track file, (bonus or not).
   *
   * @param filePath The filepath to be inspected.
   * @return Whether the file is a subtitle track.
   */
  def episodeSubtitleFilePredicate(filePath: String): Boolean

  /**
   * Whether the file is an episode video file, (bonus or not).
   *
   * @param filePath The filepath to be inspected.
   * @return Whether the file is an episode video file.
   */
  def episodeVideoFilePredicate(filePath: String): Boolean

  /**
   * Whether the file is related to a bonus episode, either subtitle track or video file.
   *
   * @param filePath The filepath to be inspected.
   * @return Whether the file is bonus material, (bonus video or subtitle track).
   */
  def episodeBonusFilePredicate(filePath: String): Boolean

  /**
   * Whether a file is an episode file, either subtitle track or video file.
   *
   * @param filePath The filepath to be inspected.
   * @return Whether the file is an episode-related file.
   */
  def episodeFilePredicate(filePath: String): Boolean =
    episodeVideoFilePredicate(filePath) || episodeSubtitleFilePredicate(filePath)

  /**
   * Whether an episode file (either subtitle track or video file) is in a serial.
   *
   * @param filePath     The filepath to be inspected.
   * @param serialNumber The (absolute) serial number to compare against.
   * @return Whether the episode file is part of the serial.
   */
  def episodeFileInSerialPredicate(filePath: String, serialNumber: Int): Boolean