package com.moviecatalogue.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moviecatalogue.core.data.local.entity.*
import com.moviecatalogue.core.data.local.entity.favorite.FavoriteEntity

@Database(
    entities = [
        DiscoverMovieEntity::class,
        NowPlayingMovieEntity::class,
        UpcomingMovieEntity::class,
        DiscoverTvShowEntity::class,
        AiringTodayTvShowEntity::class,
        OnTheAirTvShowEntity::class,
        FavoriteEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class FilmDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
}