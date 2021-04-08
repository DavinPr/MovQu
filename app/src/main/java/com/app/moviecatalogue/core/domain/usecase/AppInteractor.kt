package com.app.moviecatalogue.core.domain.usecase

import com.app.moviecatalogue.core.data.AppRepository
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.core.domain.usecase.model.TvShow
import kotlinx.coroutines.flow.Flow

class AppInteractor(private val appRepository: AppRepository) : AppUseCase {
    override fun getListMovieDiscover(): Flow<Resource<List<Movie>>> =
        appRepository.getListMovieDiscover()

    override fun getListMovieNowPlaying(): Flow<Resource<List<Movie>>> =
        appRepository.getListMovieNowPlaying()

    override fun getListMovieUpcoming(): Flow<Resource<List<Movie>>> =
        appRepository.getListMovieUpcoming()

    override fun getListTvDiscover(): Flow<Resource<List<TvShow>>> =
        appRepository.getListTvDiscover()

    override fun getListTvAiringToday(): Flow<Resource<List<TvShow>>> =
        appRepository.getListTvAiringToday()

    override fun getListTvOnTheAir(): Flow<Resource<List<TvShow>>> =
        appRepository.getListTvOnTheAir()
}