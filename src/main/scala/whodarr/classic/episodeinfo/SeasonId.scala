package whodarr.classic.episodeinfo

opaque type SeasonId = Int

object SeasonId:
  def apply(value: Int): SeasonId = value

  extension (sn: SeasonId) def value: Int = sn
