package com.moviecatalogue.core.domain.usecase.model

data class Movie(
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val voteAverage: Double? = null,
    val id: Int? = null
)
