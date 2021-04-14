package com.app.moviecatalogue.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @field:SerializedName("success")
    val success: Boolean = true,
    @field:SerializedName("backdrop_path")
    val backdrop_path: String,
    @field:SerializedName("genres")
    val genres: List<DetailMovieGenre>,
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("overview")
    val overview: String,
    @field:SerializedName("popularity")
    val popularity: Double,
    @field:SerializedName("poster_path")
    val poster_path: String,
    @field:SerializedName("release_date")
    val release_date: String,
    @field:SerializedName("runtime")
    val runtime: Int,
    @field:SerializedName("title")
    val title: String,
    @field:SerializedName("vote_average")
    val vote_average: Double,
    @field:SerializedName("tagline")
    val tagline: String
)

data class DetailMovieGenre(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String
)