package whodarr.classic.types

/**
 *
 * @param season The season which the serial is placed in.
 * @param relativeEpisodeOffset The offset of the serial in the season, in episode-units.
 * @param absoluteStoryNumber The inter-seasonal story number of the serial.
 * @param relativeStoryNumber The story number within the season.
 */
case class SerialId(season: Int, relativeEpisodeOffset: Int, absoluteStoryNumber: Int, relativeStoryNumber: Int)
