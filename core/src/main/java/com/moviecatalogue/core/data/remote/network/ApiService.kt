package com.moviecatalogue.core.data.remote.network

import com.moviecatalogue.core.BuildConfig
import com.moviecatalogue.core.data.remote.response.MovieDetailResponse
import com.moviecatalogue.core.data.remote.response.MovieResponse
import com.moviecatalogue.core.data.remote.response.TvDetailResponse
import com.moviecatalogue.core.data.remote.response.TvShowResponse
import com.moviecatalogue.core.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getListMovieDiscover(
        @Query("api_key") apiKey: String = Constants.getApiKeys(),
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getListMovieNowPlaying(
        @Query("api_key") apiKey: String = Constants.getApiKeys(),
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getListMovieUpcoming(
        @Query("api_key") apiKey: String = Constants.getApiKeys(),
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("discover/tv")
    suspend fun getListTvDiscover(
        @Query("api_key") apiKey: String = Constants.getApiKeys(),
        @Query("page") page: Int = 1
    ): TvShowResponse

    @GET("tv/airing_today")
    suspend fun getListTvAiringToday(
        @Query("api_key") apiKey: String = Constants.getApiKeys(),
        @Query("page") page: Int = 1
    ): TvShowResponse

    @GET("tv/on_the_air")
    suspend fun getListTvOnTheAir(
        @Query("api_key") apiKey: String = Constants.getApiKeys(),
        @Query("page") page: Int = 1
    ): TvShowResponse

    @GET("movie/{id}")
    suspend fun getDetailMovie(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = Constants.getApiKeys()
    ): MovieDetailResponse

    @GET("tv/{id}")
    suspend fun getDetailTv(
        @Path("id") id: String,
        @Query("api_key") apiKey: String = Constants.getApiKeys()
    ): TvDetailResponse
}