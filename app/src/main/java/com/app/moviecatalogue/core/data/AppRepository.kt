package com.app.moviecatalogue.core.data

import androidx.room.withTransaction
import com.app.moviecatalogue.core.data.local.LocalDataSource
import com.app.moviecatalogue.core.data.remote.RemoteDataSource
import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.core.domain.usecase.model.TvShow
import com.app.moviecatalogue.core.utils.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IAppRepository {

    override fun getListMovieDiscover(): Flow<Resource<List<Movie>>> =
        networkBoundResource(
            query = {
                localDataSource.getAllMovieDiscover().map {
                    it.toMovieDomain()
                }
            },

            fetch = {
                delay(2000)
                remoteDataSource.getListDiscoverMovie()
            },

            saveFetchResult = { movies ->
                localDataSource.filmDatabase.withTransaction {
                    localDataSource.deleteAllMovieDiscover()
                    localDataSource.insertListMovieDiscover(movies.toDiscoverMovieEntity())
                }
            }
        )

    override fun getListMovieNowPlaying(): Flow<Resource<List<Movie>>> =
        networkBoundResource(
            query = {
                localDataSource.getAllMovieNowPlaying().map {
                    it.toMovieDomain()
                }
            },

            fetch = {
                delay(2000)
                remoteDataSource.getListMovieNowPlaying()
            },

            saveFetchResult = { movies ->
                localDataSource.filmDatabase.withTransaction {
                    localDataSource.deleteAllMovieNowPlaying()
                    localDataSource.insertListMovieNowPlaying(movies.toNowPlayingMovieEntity())
                }
            }
        )

    override fun getListMovieUpcoming(): Flow<Resource<List<Movie>>> =
        networkBoundResource(
            query = {
                localDataSource.getAllMovieUpcoming().map {
                    it.toMovieDomain()
                }
            },

            fetch = {
                delay(2000)
                remoteDataSource.getListMovieUpcoming()
            },

            saveFetchResult = { movies ->
                localDataSource.filmDatabase.withTransaction {
                    localDataSource.deleteAllMovieUpcoming()
                    localDataSource.insertListMovieUpcoming(movies.toUpcomingMovieEntity())
                }
            }
        )

    override fun getListTvDiscover(): Flow<Resource<List<TvShow>>> =
        networkBoundResource(
            query = {
                localDataSource.getAllTvShowDiscover().map {
                    it.toTvShowDomain()
                }
            },

            fetch = {
                delay(2000)
                remoteDataSource.getListTvDiscover()
            },

            saveFetchResult = { movies ->
                localDataSource.filmDatabase.withTransaction {
                    localDataSource.deleteAllTvShowDiscover()
                    localDataSource.insertListTvShowDiscover(movies.toDiscoverTvShowEntity())
                }
            }
        )

    override fun getListTvAiringToday(): Flow<Resource<List<TvShow>>> =
        networkBoundResource(
            query = {
                localDataSource.getAllTvShowAiringToday().map {
                    it.toTvShowDomain()
                }
            },

            fetch = {
                delay(2000)
                remoteDataSource.getListTvAiringToday()
            },

            saveFetchResult = { movies ->
                localDataSource.filmDatabase.withTransaction {
                    localDataSource.deleteAllTvShowAiringToday()
                    localDataSource.insertListTvShowAiringToday(movies.toAiringTodayTvShowEntity())
                }
            }
        )

    override fun getListTvOnTheAir(): Flow<Resource<List<TvShow>>> =
        networkBoundResource(
            query = {
                localDataSource.getAllTvShowOnTheAir().map {
                    it.toTvShowDomain()
                }
            },

            fetch = {
                delay(2000)
                remoteDataSource.getListTvOnTheAir()
            },

            saveFetchResult = { movies ->
                localDataSource.filmDatabase.withTransaction {
                    localDataSource.deleteAllTvShowOnTheAir()
                    localDataSource.insertListTvShowOnTheAir(movies.toOnTheAirTvShowEntity())
                }
            }
        )

}