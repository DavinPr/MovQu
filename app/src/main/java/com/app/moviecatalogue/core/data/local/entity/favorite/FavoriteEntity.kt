package com.app.moviecatalogue.core.data.local.entity.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @ColumnInfo(name = "title")
    val title: String? = null,

    @ColumnInfo(name = "poster")
    val posterPath: String? = null,

    @ColumnInfo(name = "type")
    val type: String? = null,

    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int? = null
)
