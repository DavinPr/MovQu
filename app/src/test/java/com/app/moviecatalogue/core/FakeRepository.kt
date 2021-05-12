package com.app.moviecatalogue.core

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.withTransaction
import com.app.moviecatalogue.Constants
import com.app.moviecatalogue.core.data.IAppRepository
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.data.local.LocalDataSource
import com.app.moviecatalogue.core.data.networkBoundResource
import com.app.moviecatalogue.core.data.remote.RemoteDataSource
import com.app.moviecatalogue.core.data.remote.network.ApiResponse
import com.app.moviecatalogue.core.domain.usecase.model.*
import com.app.moviecatalogue.core.utils.*
import com.app.moviecatalogue.presentation.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import timber.log.Timber

class FakeRepository constructor(
    private val appExecutors: AppExecutors,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : IAppRepository {
    override fun getAllListMovieDiscover(): Flow<Resource<PagedList<Movie>>> =
        flow {
            emit(Resource.Loading())
            val moviesDataSource =
                remoteDataSource.getAllListDiscoverMovie().map { it.toMovie() }
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(Constants.POST_PER_PAGE)
                .build()
            val moviePagedList = LivePagedListBuilder(moviesDataSource, config).build()
            moviePagedList.asFlow().collect {
                emit(Resource.Success(it))
            }
        }.catch { e ->
            emit(Resource.Error(e.toString()))
            Timber.e(e)
        }.flowOn(Dispatchers.IO)

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

    override fun getAllListMovieNowPlaying(): Flow<Resource<PagedList<Movie>>> =
        flow {
            emit(Resource.Loading())
            val moviesDataSource =
                remoteDataSource.getAllListNowPlayingMovie().map { it.toMovie() }
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(Constants.POST_PER_PAGE)
                .build()
            val moviePagedList = LivePagedListBuilder(moviesDataSource, config).build()
            moviePagedList.asFlow().collect {
                emit(Resource.Success(it))
            }
        }.catch { e ->
            emit(Resource.Error(e.toString()))
            Timber.e(e)
        }.flowOn(Dispatchers.IO)

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

    override fun getAllListMovieUpcoming(): Flow<Resource<PagedList<Movie>>> =
        flow {
            emit(Resource.Loading())
            val moviesDataSource =
                remoteDataSource.getAllListUpcomingMovie().map { it.toMovie() }
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(Constants.POST_PER_PAGE)
                .build()
            val moviePagedList = LivePagedListBuilder(moviesDataSource, config).build()
            moviePagedList.asFlow().collect {
                emit(Resource.Success(it))
            }
        }.catch { e ->
            emit(Resource.Error(e.toString()))
            Timber.e(e)
        }.flowOn(Dispatchers.IO)

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

    override fun getAllListTvDiscover(): Flow<Resource<PagedList<TvShow>>> =
        flow {
            emit(Resource.Loading())
            val tvDataSource =
                remoteDataSource.getAllListDiscoverTv().map { it.toTvShow() }
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(Constants.POST_PER_PAGE)
                .build()
            val tvPagedList = LivePagedListBuilder(tvDataSource, config).build()
            tvPagedList.asFlow().collect {
                emit(Resource.Success(it))
            }
        }.catch { e ->
            emit(Resource.Error(e.toString()))
            Timber.e(e)
        }.flowOn(Dispatchers.IO)

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

            saveFetchResult = { tv ->
                localDataSource.filmDatabase.withTransaction {
                    localDataSource.deleteAllTvShowDiscover()
                    localDataSource.insertListTvShowDiscover(tv.toDiscoverTvShowEntity())
                }
            }
        )

    override fun getAllListTvAiringToday(): Flow<Resource<PagedList<TvShow>>> =
        flow {
            emit(Resource.Loading())
            val tvDataSource =
                remoteDataSource.getAllListAiringTodayTv().map { it.toTvShow() }
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(Constants.POST_PER_PAGE)
                .build()
            val tvPagedList = LivePagedListBuilder(tvDataSource, config).build()
            tvPagedList.asFlow().collect {
                emit(Resource.Success(it))
            }
        }.catch { e ->
            emit(Resource.Error(e.toString()))
            Timber.e(e)
        }.flowOn(Dispatchers.IO)

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

            saveFetchResult = { tv ->
                localDataSource.filmDatabase.withTransaction {
                    localDataSource.deleteAllTvShowAiringToday()
                    localDataSource.insertListTvShowAiringToday(tv.toAiringTodayTvShowEntity())
                }
            }
        )

    override fun getAllListTvOnTheAir(): Flow<Resource<PagedList<TvShow>>> =
        flow {
            emit(Resource.Loading())
            val tvDataSource =
                remoteDataSource.getAllListOnTheAirTv().map { it.toTvShow() }
            val config = PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(Constants.POST_PER_PAGE)
                .build()
            val tvPagedList = LivePagedListBuilder(tvDataSource, config).build()
            tvPagedList.asFlow().collect {
                emit(Resource.Success(it))
            }
        }.catch { e ->
            emit(Resource.Error(e.toString()))
            Timber.e(e)
        }.flowOn(Dispatchers.IO)

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

            saveFetchResult = { tv ->
                localDataSource.filmDatabase.withTransaction {
                    localDataSource.deleteAllTvShowOnTheAir()
                    localDataSource.insertListTvShowOnTheAir(tv.toOnTheAirTvShowEntity())
                }
            }
        )

    override fun getDetailMovie(id: String): Flow<Resource<MovieDetail>> =
        flow {
            EspressoIdlingResource.increment()
            when (val apiResponse = remoteDataSource.getDetailMovie(id).first()) {
                is ApiResponse.Success -> {
                    val data = apiResponse.data.toMovieDetailDomain()
                    emit(Resource.Success(data))
                }
                is ApiResponse.Empty -> emit(Resource.Success(MovieDetail()))
                is ApiResponse.Error -> emit(Resource.Error<MovieDetail>(apiResponse.errorMessage))
            }
        }

    override fun getDetailTv(id: String): Flow<Resource<TvDetail>> =
        flow {
            when (val apiResponse = remoteDataSource.getDetailTv(id).first()) {
                is ApiResponse.Success -> {
                    val data = apiResponse.data.toTvDetailDomain()
                    emit(Resource.Success(data))
                }
                is ApiResponse.Empty -> emit(Resource.Success(TvDetail()))
                is ApiResponse.Error -> emit(Resource.Error<TvDetail>(apiResponse.errorMessage))
            }
        }

    override fun getAllFavorite(): LiveData<PagedList<Favorite>> {
        val data = localDataSource.getAllFavorite().map { it.toDomain() }
        return LivePagedListBuilder(data, 12).build()
    }

    override fun getFavoriteByType(type: String): LiveData<PagedList<Favorite>> {
        val data = localDataSource.getFavoriteByType(type).map { it.toDomain() }
        return LivePagedListBuilder(data, 12).build()
    }

    override fun insertFavorite(favorite: Favorite) {
        appExecutors.diskIO().execute {
            localDataSource.insertFavorite(favorite.toEntity())
        }
    }

    override fun deleteFavorite(favorite: Favorite) {
        appExecutors.diskIO().execute {
            localDataSource.deleteFavorite(favorite.toEntity())
        }
    }

    override fun isFavorited(id: String): Flow<Boolean> = localDataSource.isFavorited(id)

}