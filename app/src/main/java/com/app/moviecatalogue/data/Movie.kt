package com.app.moviecatalogue.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val releaseDate: String? = null,
    val voteAverage: Double? = null,
    val id: Int? = null,
    val runtime: Int? = null,
    val genres: List<String>? = null,
    val overview: String? = null,
    val tagline: String? = null
) : Parcelable
