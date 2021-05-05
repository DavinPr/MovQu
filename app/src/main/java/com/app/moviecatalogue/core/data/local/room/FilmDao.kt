package com.app.moviecatalogue.core.data.local.room

import androidx.paging.DataSource
import androidx.room.*
import com.app.moviecatalogue.core.data.local.entity.*
import com.app.moviecatalogue.core.data.local.entity.favorite.FavoriteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    /**
     * Query for Discover Movie
     * Homepage
     */
    @Query("SELECT * FROM discover_movie")
    fun getAllMovieDiscover(): Flow<List<DiscoverMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListMovieDiscover(listDiscoverMovieEntity: List<DiscoverMovieEntity>)

    @Query("DELETE FROM discover_movie")
    suspend fun deleteAllMovieDiscover()

    /**
     * Query for Now Playing Movie
     * Homepage
     */
    @Query("SELECT * FROM now_playing_movie")
    fun getAllMovieNowPlaying(): Flow<List<NowPlayingMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListMovieNowPlaying(listNowPlayingMovieEntity: List<NowPlayingMovieEntity>)

    @Query("DELETE FROM now_playing_movie")
    suspend fun deleteAllMovieNowPlaying()

    /**
     * Query for Upcoming Movie
     * Homepage
     */
    @Query("SELECT * FROM upcoming_movie")
    fun getAllMovieUpcoming(): Flow<List<UpcomingMovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListMovieUpcoming(listUpcomingMovieEntity: List<UpcomingMovieEntity>)

    @Query("DELETE FROM upcoming_movie")
    suspend fun deleteAllMovieUpcoming()

    /**
     * Query for Upcoming Tv Show
     * Homepage
     */
    @Query("SELECT * FROM discover_tv_show")
    fun getAllTvShowDiscover(): Flow<List<DiscoverTvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListTvShowDiscover(listDiscoverTvShowEntity: List<DiscoverTvShowEntity>)

    @Query("DELETE FROM discover_tv_show")
    suspend fun deleteAllTvShowDiscover()

    /**
     * Query for Airing Today Tv Show
     * Homepage
     */
    @Query("SELECT * FROM airing_today_tv_show")
    fun getAllTvShowAiringToday(): Flow<List<AiringTodayTvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListTvShowAiringToday(listAiringTodayTvShowEntity: List<AiringTodayTvShowEntity>)

    @Query("DELETE FROM airing_today_tv_show")
    suspend fun deleteAllTvShowAiringToday()


    /**
     * Query for On The Air Tv Show
     * Homepage
     */
    @Query("SELECT * FROM on_the_air_tv_show")
    fun getAllTvShowOnTheAir(): Flow<List<OnTheAirTvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertListTvShowOnTheAir(listOnTheAirTvShowEntity: List<OnTheAirTvShowEntity>)

    @Query("DELETE FROM on_the_air_tv_show")
    suspend fun deleteAllTvShowOnTheAir()

    /**
     * Query for Movie Favorite
     */
    @Query("SELECT * FROM favorite")
    fun getAllFavorite(): DataSource.Factory<Int, FavoriteEntity>

    @Query("SELECT * FROM favorite WHERE type = :type")
    fun getFavoriteByType(type: String): DataSource.Factory<Int, FavoriteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavorite(favorite: FavoriteEntity)

    @Delete
    fun deleteFavorite(favorite: FavoriteEntity)

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE id = :id)")
    fun isFavorited(id: String): Flow<Boolean>


}