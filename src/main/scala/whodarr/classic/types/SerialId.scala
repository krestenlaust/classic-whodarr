package whodarr.classic.types

/**
 *
 * @param season The season which the serial is placed in.
 * @param firstEpisode The first episode of the serial.
 * @param episodeCount The count of episodes in this serial.
 * @param storyNumber The story number within the season.
 * @param totalStoryNumber The inter-seasonal story number of the serial.
 */
case class SerialId(season: SeasonId, firstEpisode: EpisodeId, episodeCount: Int, storyNumber: Int, totalStoryNumber: Int)
