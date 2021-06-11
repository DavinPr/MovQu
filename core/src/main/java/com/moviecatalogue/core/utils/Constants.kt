package com.moviecatalogue.core.utils

object Constants {
    const val ID_KEY = "id_key"
    const val TYPE_KEY = "type_key"
    const val LIST_TYPE = "list_type"

    const val MOVIE_TYPE = 101
    const val TV_TYPE = 102

    const val FIRST_PAGE = 1
    const val POST_PER_PAGE = 20

    const val MOVIE_DISCOVER_TYPE = 201
    const val MOVIE_NOW_PLAYING_TYPE = 202
    const val MOVIE_UPCOMING_TYPE = 203

    const val TV_DISCOVER_TYPE = 301
    const val TV_AIRING_TODAY_TYPE = 302
    const val TV_ON_THE_AIR_TYPE = 303

    init {
        System.loadLibrary("api-keys")
    }
    external fun getApiKeys() : String
    external fun getBaseUrl() : String
    external fun getHostName() : String
}