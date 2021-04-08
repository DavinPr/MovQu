package com.app.moviecatalogue.core.data.remote.network

import com.app.moviecatalogue.BuildConfig
import com.app.moviecatalogue.core.data.remote.response.MovieResponse
import com.app.moviecatalogue.core.data.remote.response.TvShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("discover/movie")
    suspend fun getListMovieDiscover(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET("movie/now_playing")
    suspend fun getListMovieNowPlaying(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET("movie/upcoming")
    suspend fun getListMovieUpcoming(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieResponse

    @GET("discover/tv")
    suspend fun getListTvDiscover(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): TvShowResponse

    @GET("tv/airing_today")
    suspend fun getListTvAiringToday(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): TvShowResponse

    @GET("tv/on_the_air")
    suspend fun getListTvOnTheAir(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): TvShowResponse
}