package com.app.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.app.moviecatalogue.core.data.IAppRepository
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.domain.usecase.model.*
import com.app.moviecatalogue.core.utils.toFavorite
import kotlinx.coroutines.flow.Flow

class AppInteractor(private val appRepository: IAppRepository) : AppUseCase {
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