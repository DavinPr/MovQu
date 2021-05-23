package com.moviecatalogue.core.utils

import com.moviecatalogue.core.data.local.entity.*
import com.moviecatalogue.core.data.local.entity.favorite.FavoriteEntity
import com.moviecatalogue.core.data.remote.response.MovieDetailResponse
import com.moviecatalogue.core.data.remote.response.MoviesItem
import com.moviecatalogue.core.data.remote.response.TvDetailResponse
import com.moviecatalogue.core.data.remote.response.TvShowItem
import com.moviecatalogue.core.domain.usecase.model.*

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

fun MovieDetailResponse.toMovieDetailDomain(): MovieDetail {
    val genres = ArrayList<MovieGenre>()
    this.genres.map {
        val genre = MovieGenre(
            id = it.id,
            name = it.name
        )
        genres.add(genre)
    }

    return MovieDetail(
        backdrop_path = this.backdrop_path,
        genres = genres,
        id = this.id,
        overview = this.overview,
        popularity = this.popularity,
        poster_path = this.poster_path,
        release_date = this.release_date,
        runtime = this.runtime,
        title = this.title,
        vote_average = this.vote_average,
        tagline = this.tagline
    )
}

fun TvDetailResponse.toTvDetailDomain(): TvDetail {
    val genres = ArrayList<TvGenre>()
    this.genres?.map {
        val genre = TvGenre(
            id = it.id,
            name = it.name
        )
        genres.add(genre)
    }

    val seasons = ArrayList<TvSeasons>()
    this.seasons?.map {
        val season = TvSeasons(
            airDate = it.airDate,
            overview = it.overview,
            episodeCount = it.episodeCount,
            name = it.name,
            seasonNumber = it.seasonNumber,
            id = it.id,
            posterPath = it.posterPath
        )
        seasons.add(season)
    }

    return TvDetail(
        numberOfEpisodes = this.numberOfEpisodes,
        backdropPath = this.backdropPath,
        genres = genres,
        popularity = this.popularity,
        id = this.id,
        numberOfSeasons = this.numberOfSeasons,
        firstAirDate = this.firstAirDate,
        overview = this.overview,
        seasons = seasons,
        posterPath = this.posterPath,
        voteAverage = this.voteAverage,
        name = this.name,
        tagline = this.tagline,
        episodeRunTime = this.episodeRunTime,
        lastAirDate = this.lastAirDate
    )
}

fun MoviesItem.toMovie(): Movie {
    return Movie(
        title = this.title,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath,
        releaseDate = this.releaseDate,
        voteAverage = this.voteAverage,
        id = this.id
    )
}

fun TvShowItem.toTvShow(): TvShow {
    return TvShow(
        name = this.name,
        posterPath = this.posterPath,
        backdropPath = this.backdropPath,
        firstAirDate = this.firstAirDate,
        voteAverage = this.voteAverage,
        id = this.id
    )
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

fun FavoriteEntity?.toDomain(): Favorite {
    return this.let {
        Favorite(
            it?.title,
            it?.posterPath,
            it?.type,
            it?.id
        )
    }
}

fun Favorite.toEntity(): FavoriteEntity {
    return this.let {
        FavoriteEntity(
            it.title,
            it.posterPath,
            it.type,
            it.id
        )
    }
}

fun MovieDetail.toFavorite(): Favorite {
    return this.let {
        Favorite(
            it.title,
            it.poster_path,
            "movie",
            it.id
        )
    }
}

fun TvDetail.toFavorite(): Favorite {
    return this.let {
        Favorite(
            it.name,
            it.posterPath,
            "tv",
            it.id
        )
    }
}


