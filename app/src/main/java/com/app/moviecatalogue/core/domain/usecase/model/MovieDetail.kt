package com.app.moviecatalogue.core.domain.usecase.model

data class MovieDetail(
    val backdrop_path: String,
    val genres: List<MovieGenre>,
    val id: Int,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val runtime: Int,
    val title: String,
    val vote_average: Double,
    val tagline: String
)

data class MovieGenre(
    val id: Int,
    val name: String
)