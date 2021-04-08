package com.app.moviecatalogue.core.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "discover_tv_show")
data class DiscoverTvShowEntity(
    @ColumnInfo(name = "name")
    val name: String? = null,

    @ColumnInfo(name = "poster")
    val posterPath: String? = null,

    @ColumnInfo(name = "backdrop")
    val backdropPath: String? = null,

    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String? = null,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double? = null,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null
)
