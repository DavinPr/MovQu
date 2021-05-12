package com.app.moviecatalogue.core.data.remote.response

import com.google.gson.annotations.SerializedName

data class TvShowResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int = 0,

	@field:SerializedName("results")
	val results: List<TvShowItem>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)

data class TvShowItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("vote_average")
	val voteAverage: Double? = null,

	@field:SerializedName("id")
	val id: Int? = null
)
