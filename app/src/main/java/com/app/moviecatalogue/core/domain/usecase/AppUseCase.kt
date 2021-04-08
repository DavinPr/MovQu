package com.app.moviecatalogue.core.domain.usecase

import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.core.domain.usecase.model.TvShow
import kotlinx.coroutines.flow.Flow

interface AppUseCase {
    fun getListMovieDiscover(): Flow<Resource<List<Movie>>>

    fun getListMovieNowPlaying(): Flow<Resource<List<Movie>>>

    fun getListMovieUpcoming(): Flow<Resource<List<Movie>>>

    fun getListTvDiscover(): Flow<Resource<List<TvShow>>>

    fun getListTvAiringToday(): Flow<Resource<List<TvShow>>>

    fun getListTvOnTheAir(): Flow<Resource<List<TvShow>>>
}