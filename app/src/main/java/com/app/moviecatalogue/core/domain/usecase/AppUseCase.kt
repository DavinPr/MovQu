package com.app.moviecatalogue.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.domain.usecase.model.*
import kotlinx.coroutines.flow.Flow

interface AppUseCase {
    fun getAllListMovieDiscover(): Flow<Resource<PagedList<Movie>>>

    fun getListMovieDiscover(): Flow<Resource<List<Movie>>>

    fun getAllListMovieNowPlaying(): Flow<Resource<PagedList<Movie>>>

    fun getListMovieNowPlaying(): Flow<Resource<List<Movie>>>

    fun getAllListMovieUpcoming(): Flow<Resource<PagedList<Movie>>>

    fun getListMovieUpcoming(): Flow<Resource<List<Movie>>>

    fun getAllListTvDiscover(): Flow<Resource<PagedList<TvShow>>>

    fun getListTvDiscover(): Flow<Resource<List<TvShow>>>

    fun getAllListTvAiringToday(): Flow<Resource<PagedList<TvShow>>>

    fun getListTvAiringToday(): Flow<Resource<List<TvShow>>>

    fun getAllListTvOnTheAir(): Flow<Resource<PagedList<TvShow>>>

    fun getListTvOnTheAir(): Flow<Resource<List<TvShow>>>

    fun getDetailMovie(id: String): Flow<Resource<MovieDetail>>

    fun getDetailTv(id: String): Flow<Resource<TvDetail>>

    fun getAllFavorite(): LiveData<PagedList<Favorite>>

    fun getFavoriteByType(type: String): LiveData<PagedList<Favorite>>

    fun insertFavoriteFromMovie(detail: MovieDetail)

    fun insertFavoriteFromTv(detail: TvDetail)

    fun deleteFavoriteFromMovie(detail: MovieDetail)

    fun deleteFavoriteFromTv(detail: TvDetail)

    fun isFavorited(id: String): Flow<Boolean>
}