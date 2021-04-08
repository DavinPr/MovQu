package com.app.moviecatalogue.core.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.app.moviecatalogue.core.data.local.entity.*
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

}