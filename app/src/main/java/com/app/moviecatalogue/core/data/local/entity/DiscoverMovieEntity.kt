package com.app.moviecatalogue.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "discover_movie")
data class DiscoverMovieEntity(
    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "poster")
    val posterPath: String? = null,

    @ColumnInfo(name = "backdrop")
    val backdropPath: String? = null,

    @ColumnInfo(name = "release_date")
    val releaseDate: String? = null,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double? = null,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null
)
