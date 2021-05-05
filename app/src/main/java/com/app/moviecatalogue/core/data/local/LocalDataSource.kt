package com.app.moviecatalogue.core.data.local

import androidx.paging.DataSource
import com.app.moviecatalogue.core.data.local.entity.*
import com.app.moviecatalogue.core.data.local.entity.favorite.FavoriteEntity
import com.app.moviecatalogue.core.data.local.room.FilmDatabase
import kotlinx.coroutines.flow.Flow

class LocalDataSource(val filmDatabase: FilmDatabase) {

    private val filmDao = filmDatabase.filmDao()

    /**
     * Discover Movie Source
     *
     */
    fun getAllMovieDiscover(): Flow<List<DiscoverMovieEntity>> = filmDao.getAllMovieDiscover()
    suspend fun insertListMovieDiscover(listDiscoverMovieEntity: List<DiscoverMovieEntity>) =
        filmDao.insertListMovieDiscover(listDiscoverMovieEntity)

    suspend fun deleteAllMovieDiscover() = filmDao.deleteAllMovieDiscover()

    /**
     * Now Playing Movie Source
     *
     */
    fun getAllMovieNowPlaying(): Flow<List<NowPlayingMovieEntity>> = filmDao.getAllMovieNowPlaying()
    suspend fun insertListMovieNowPlaying(listNowPlayingMovieEntity: List<NowPlayingMovieEntity>) =
        filmDao.insertListMovieNowPlaying(listNowPlayingMovieEntity)

    suspend fun deleteAllMovieNowPlaying() = filmDao.deleteAllMovieNowPlaying()

    /**
     * Upcoming Movie Source
     *
     */
    fun getAllMovieUpcoming(): Flow<List<UpcomingMovieEntity>> = filmDao.getAllMovieUpcoming()
    suspend fun insertListMovieUpcoming(listUpcomingMovieEntity: List<UpcomingMovieEntity>) =
        filmDao.insertListMovieUpcoming(listUpcomingMovieEntity)

    suspend fun deleteAllMovieUpcoming() = filmDao.deleteAllMovieUpcoming()

    /**
     * Discover Tv Show Source
     *
     */
    fun getAllTvShowDiscover(): Flow<List<DiscoverTvShowEntity>> = filmDao.getAllTvShowDiscover()
    suspend fun insertListTvShowDiscover(listDiscoverTvShowEntity: List<DiscoverTvShowEntity>) =
        filmDao.insertListTvShowDiscover(listDiscoverTvShowEntity)

    suspend fun deleteAllTvShowDiscover() = filmDao.deleteAllTvShowDiscover()

    /**
     * Airing Today Tv Show Source
     *
     */
    fun getAllTvShowAiringToday(): Flow<List<AiringTodayTvShowEntity>> =
        filmDao.getAllTvShowAiringToday()

    suspend fun insertListTvShowAiringToday(listAiringTodayTvShowEntity: List<AiringTodayTvShowEntity>) =
        filmDao.insertListTvShowAiringToday(listAiringTodayTvShowEntity)

    suspend fun deleteAllTvShowAiringToday() = filmDao.deleteAllTvShowAiringToday()

    /**
     * On The Air Tv Show Source
     *
     */
    fun getAllTvShowOnTheAir(): Flow<List<OnTheAirTvShowEntity>> = filmDao.getAllTvShowOnTheAir()
    suspend fun insertListTvShowOnTheAir(listOnTheAirTvShowEntity: List<OnTheAirTvShowEntity>) =
        filmDao.insertListTvShowOnTheAir(listOnTheAirTvShowEntity)

    suspend fun deleteAllTvShowOnTheAir() = filmDao.deleteAllTvShowOnTheAir()

    /**
     * Favorite Source
     */
    fun getAllFavorite(): DataSource.Factory<Int, FavoriteEntity> = filmDao.getAllFavorite()

    fun getFavoriteByType(type: String): DataSource.Factory<Int, FavoriteEntity> =
        filmDao.getFavoriteByType(type)

    fun insertFavorite(favorite: FavoriteEntity) = filmDao.insertFavorite(favorite)

    fun deleteFavorite(favorite: FavoriteEntity) = filmDao.deleteFavorite(favorite)

    fun isFavorited(id: String): Flow<Boolean> = filmDao.isFavorited(id)

}