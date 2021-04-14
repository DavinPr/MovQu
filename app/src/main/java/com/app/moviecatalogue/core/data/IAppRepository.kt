package com.app.moviecatalogue.core.data

import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.core.domain.usecase.model.TvShow
import kotlinx.coroutines.flow.Flow

interface IAppRepository {
    fun getListMovieDiscover(): Flow<Resource<List<Movie>>>

    fun getListMovieNowPlaying(): Flow<Resource<List<Movie>>>

    fun getListMovieUpcoming(): Flow<Resource<List<Movie>>>

    fun getListTvDiscover(): Flow<Resource<List<TvShow>>>

    fun getListTvAiringToday(): Flow<Resource<List<TvShow>>>

    fun getListTvOnTheAir(): Flow<Resource<List<TvShow>>>


}