package com.app.moviecatalogue.core.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.app.moviecatalogue.core.domain.usecase.model.*
import kotlinx.coroutines.flow.Flow

interface IAppRepository {
    fun getListMovieDiscover(): Flow<Resource<List<Movie>>>

    fun getListMovieNowPlaying(): Flow<Resource<List<Movie>>>

    fun getListMovieUpcoming(): Flow<Resource<List<Movie>>>

    fun getListTvDiscover(): Flow<Resource<List<TvShow>>>

    fun getListTvAiringToday(): Flow<Resource<List<TvShow>>>

    fun getListTvOnTheAir(): Flow<Resource<List<TvShow>>>

    fun getDetailMovie(id: String): Flow<Resource<MovieDetail>>

    fun getDetailTv(id: String): Flow<Resource<TvDetail>>

    fun getAllFavorite(): LiveData<PagedList<Favorite>>

    fun getFavoriteByType(type: String): LiveData<PagedList<Favorite>>

    fun insertFavorite(favorite: Favorite)

    fun deleteFavorite(favorite: Favorite)

    fun isFavorited(id: String): Flow<Boolean>
}