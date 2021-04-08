package com.app.moviecatalogue.core.utils

import com.app.moviecatalogue.core.data.local.entity.*
import com.app.moviecatalogue.core.data.remote.response.MoviesItem
import com.app.moviecatalogue.core.data.remote.response.TvShowItem
import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.core.domain.usecase.model.TvShow

fun List<MoviesItem>.toDiscoverMovieEntity(): List<DiscoverMovieEntity> {
    if (this.isNullOrEmpty()) return emptyList()
    val listMovie = ArrayList<DiscoverMovieEntity>()
    this.map {
        val movie = DiscoverMovieEntity(
            title = it.title,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            releaseDate = it.releaseDate,
            voteAverage = it.voteAverage,
            id = it.id
        )
        listMovie.add(movie)
    }
    return listMovie
}

fun List<MoviesItem>.toNowPlayingMovieEntity(): List<NowPlayingMovieEntity> {
    if (this.isNullOrEmpty()) return emptyList()
    val listMovie = ArrayList<NowPlayingMovieEntity>()
    this.map {
        val movie = NowPlayingMovieEntity(
            title = it.title,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            releaseDate = it.releaseDate,
            voteAverage = it.voteAverage,
            id = it.id
        )
        listMovie.add(movie)
    }
    return listMovie
}

fun List<MoviesItem>.toUpcomingMovieEntity(): List<UpcomingMovieEntity> {
    if (this.isNullOrEmpty()) return emptyList()
    val listMovie = ArrayList<UpcomingMovieEntity>()
    this.map {
        val movie = UpcomingMovieEntity(
            title = it.title,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            releaseDate = it.releaseDate,
            voteAverage = it.voteAverage,
            id = it.id
        )
        listMovie.add(movie)
    }
    return listMovie
}

fun <T> List<T>.toMovieDomain(): List<Movie> {
    if (this.isNullOrEmpty()) return emptyList()
    val listMovie = ArrayList<Movie>()
    this.map {
        when (it) {
            is DiscoverMovieEntity -> {
                val movie = Movie(
                    title = it.title,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    releaseDate = it.releaseDate,
                    voteAverage = it.voteAverage,
                    id = it.id
                )
                listMovie.add(movie)
            }
            is NowPlayingMovieEntity -> {
                val movie = Movie(
                    title = it.title,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    releaseDate = it.releaseDate,
                    voteAverage = it.voteAverage,
                    id = it.id
                )
                listMovie.add(movie)
            }
            is UpcomingMovieEntity -> {
                val movie = Movie(
                    title = it.title,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    releaseDate = it.releaseDate,
                    voteAverage = it.voteAverage,
                    id = it.id
                )
                listMovie.add(movie)
            }
            else -> {
                listMovie.add(Movie())
            }
        }
    }
    return listMovie
}

fun List<TvShowItem>.toDiscoverTvShowEntity(): List<DiscoverTvShowEntity> {
    if (this.isNullOrEmpty()) return emptyList()
    val listTvShow = ArrayList<DiscoverTvShowEntity>()
    this.map {
        val tvShow = DiscoverTvShowEntity(
            name = it.name,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            firstAirDate = it.firstAirDate,
            voteAverage = it.voteAverage,
            id = it.id
        )
        listTvShow.add(tvShow)
    }
    return listTvShow
}

fun List<TvShowItem>.toAiringTodayTvShowEntity(): List<AiringTodayTvShowEntity> {
    if (this.isNullOrEmpty()) return emptyList()
    val listTvShow = ArrayList<AiringTodayTvShowEntity>()
    this.map {
        val tvShow = AiringTodayTvShowEntity(
            name = it.name,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            firstAirDate = it.firstAirDate,
            voteAverage = it.voteAverage,
            id = it.id
        )
        listTvShow.add(tvShow)
    }
    return listTvShow
}

fun List<TvShowItem>.toOnTheAirTvShowEntity(): List<OnTheAirTvShowEntity> {
    if (this.isNullOrEmpty()) return emptyList()
    val listTvShow = ArrayList<OnTheAirTvShowEntity>()
    this.map {
        val tvShow = OnTheAirTvShowEntity(
            name = it.name,
            posterPath = it.posterPath,
            backdropPath = it.backdropPath,
            firstAirDate = it.firstAirDate,
            voteAverage = it.voteAverage,
            id = it.id
        )
        listTvShow.add(tvShow)
    }
    return listTvShow
}

fun <T> List<T>.toTvShowDomain(): List<TvShow> {
    if (this.isNullOrEmpty()) return emptyList()
    val listTvShow = ArrayList<TvShow>()
    this.map {
        when (it) {
            is DiscoverTvShowEntity -> {
                val tvShow = TvShow(
                    name = it.name,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    firstAirDate = it.firstAirDate,
                    voteAverage = it.voteAverage,
                    id = it.id
                )
                listTvShow.add(tvShow)
            }
            is AiringTodayTvShowEntity -> {
                val tvShow = TvShow(
                    name = it.name,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    firstAirDate = it.firstAirDate,
                    voteAverage = it.voteAverage,
                    id = it.id
                )
                listTvShow.add(tvShow)
            }
            is OnTheAirTvShowEntity -> {
                val tvShow = TvShow(
                    name = it.name,
                    posterPath = it.posterPath,
                    backdropPath = it.backdropPath,
                    firstAirDate = it.firstAirDate,
                    voteAverage = it.voteAverage,
                    id = it.id
                )
                listTvShow.add(tvShow)
            }
            else -> {
                listTvShow.add(TvShow())
            }
        }
    }
    return listTvShow
}


