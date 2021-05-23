package com.moviecatalogue.core.domain.usecase.model

data class TvDetail(
	val numberOfEpisodes: Int? = null,
	val backdropPath: String? = null,
	val genres: List<TvGenre>? = null,
	val popularity: Double? = null,
	val id: Int? = null,
	val numberOfSeasons: Int? = null,
	val firstAirDate: String? = null,
	val overview: String? = null,
	val seasons: List<TvSeasons>? = null,
	val posterPath: String? = null,
	val voteAverage: Double? = null,
	val name: String? = null,
	val tagline: String? = null,
	val episodeRunTime: List<Int>? = null,
	val lastAirDate: String? = null
)

data class TvGenre(
	val name: String? = null,
	val id: Int? = null
)

data class TvSeasons(

	val airDate: String? = null,
	val overview: String? = null,
	val episodeCount: Int? = null,
	val name: String? = null,
	val seasonNumber: Int? = null,
	val id: Int? = null,
	val posterPath: String? = null
)
