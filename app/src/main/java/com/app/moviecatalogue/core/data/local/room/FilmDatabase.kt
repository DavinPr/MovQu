package com.app.moviecatalogue.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.moviecatalogue.core.data.local.entity.*

@Database(
    entities = [
        DiscoverMovieEntity::class,
        NowPlayingMovieEntity::class,
        UpcomingMovieEntity::class,
        DiscoverTvShowEntity::class,
        AiringTodayTvShowEntity::class,
        OnTheAirTvShowEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}