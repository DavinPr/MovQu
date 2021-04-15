package com.app.moviecatalogue.core.domain.usecase.model

data class MovieDetail(
    val backdrop_path: String? = null,
    val genres: List<MovieGenre>? = null,
    val id: Int? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val poster_path: String? = null,
    val release_date: String? = null,
    val runtime: Int? = null,
    val title: String? = null,
    val vote_average: Double? = null,
    val tagline: String? = null
)

data class MovieGenre(
    val id: Int? = null,
    val name: String? = null
)