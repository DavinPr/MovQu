package com.app.moviecatalogue.data

data class TvShow(
    val numberOfEpisodes: Int? = null,
    val backdropPath: String? = null,
    val genres: List<String>? = null,
    val id: Int? = null,
    val numberOfSeasons: Int? = null,
    val firstAirDate: String? = null,
    val overview: String? = null,
    val posterPath: String? = null,
    val voteAverage: Double? = null,
    val name: String? = null,
    val tagline: String? = null,
    val episodeRunTime: List<Int>? = null,
    val lastAirDate: String? = null
)
