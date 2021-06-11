package com.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.moviecatalogue.core.domain.usecase.repository.IAppRepository
import com.moviecatalogue.core.data.Resource
import com.moviecatalogue.core.domain.usecase.model.*
import com.moviecatalogue.core.utils.toFavorite
import kotlinx.coroutines.flow.Flow

class AppInteractor(private val appRepository: IAppRepository) : AppUseCase {
    override fun getAllListMovieDiscover(): Flow<Resource<PagedList<Movie>>> =
        appRepository.getAllListMovieDiscover()

    override fun getListMovieDiscover(): Flow<Resource<List<Movie>>> =
        appRepository.getListMovieDiscover()

    override fun getAllListMovieNowPlaying(): Flow<Resource<PagedList<Movie>>> =
        appRepository.getAllListMovieNowPlaying()

    override fun getListMovieNowPlaying(): Flow<Resource<List<Movie>>> =
        appRepository.getListMovieNowPlaying()

    override fun getAllListMovieUpcoming(): Flow<Resource<PagedList<Movie>>> =
        appRepository.getAllListMovieUpcoming()

    override fun getListMovieUpcoming(): Flow<Resource<List<Movie>>> =
        appRepository.getListMovieUpcoming()

    override fun getAllListTvDiscover(): Flow<Resource<PagedList<TvShow>>> =
        appRepository.getAllListTvDiscover()

    override fun getListTvDiscover(): Flow<Resource<List<TvShow>>> =
        appRepository.getListTvDiscover()

    override fun getAllListTvAiringToday(): Flow<Resource<PagedList<TvShow>>> =
        appRepository.getAllListTvAiringToday()

    override fun getListTvAiringToday(): Flow<Resource<List<TvShow>>> =
        appRepository.getListTvAiringToday()

    override fun getAllListTvOnTheAir(): Flow<Resource<PagedList<TvShow>>> =
        appRepository.getAllListTvOnTheAir()

    override fun getListTvOnTheAir(): Flow<Resource<List<TvShow>>> =
        appRepository.getListTvOnTheAir()

    override fun getDetailMovie(id: String): Flow<Resource<MovieDetail>> =
        appRepository.getDetailMovie(id)

    override fun getDetailTv(id: String): Flow<Resource<TvDetail>> = appRepository.getDetailTv(id)

    override fun getAllFavorite(): LiveData<PagedList<Favorite>> = appRepository.getAllFavorite()

    override fun getFavoriteByType(type: String): LiveData<PagedList<Favorite>> =
        appRepository.getFavoriteByType(type)

    override fun insertFavoriteFromMovie(detail: MovieDetail) {
        val favorite = detail.toFavorite()
        appRepository.insertFavorite(favorite)
    }

    override fun insertFavoriteFromTv(detail: TvDetail) {
        val favorite = detail.toFavorite()
        appRepository.insertFavorite(favorite)
    }

    override fun deleteFavoriteFromMovie(detail: MovieDetail) {
        val favorite = detail.toFavorite()
        appRepository.deleteFavorite(favorite)
    }

    override fun deleteFavoriteFromTv(detail: TvDetail) {
        val favorite = detail.toFavorite()
        appRepository.deleteFavorite(favorite)
    }

    override fun isFavorited(id: String): Flow<Boolean> = appRepository.isFavorited(id)
}