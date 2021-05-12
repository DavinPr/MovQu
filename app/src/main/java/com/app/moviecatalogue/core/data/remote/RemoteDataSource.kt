package com.app.moviecatalogue.core.data.remote

import androidx.paging.DataSource
import com.app.moviecatalogue.core.data.remote.network.ApiResponse
import com.app.moviecatalogue.core.data.remote.network.ApiService
import com.app.moviecatalogue.core.data.remote.paging.MovieDataSource
import com.app.moviecatalogue.core.data.remote.paging.TvDataSource
import com.app.moviecatalogue.core.data.remote.response.*
import com.app.moviecatalogue.presentation.utils.EspressoIdlingResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RemoteDataSource(
    private val apiService: ApiService,
    private val coroutineScope: CoroutineScope
) {

    fun getAllListDiscoverMovie(): DataSource.Factory<Int, MoviesItem> =
        object : DataSource.Factory<Int, MoviesItem>() {
            override fun create(): DataSource<Int, MoviesItem> {
                return object : MovieDataSource(coroutineScope) {
                    override val fetch: suspend (Int) -> MovieResponse
                        get() = { page ->
                            apiService.getListMovieDiscover(page = page)
                        }
                }
            }
        }


    fun getListDiscoverMovie(): Flow<ApiResponse<List<MoviesItem>>> {
        return flow {
            EspressoIdlingResource.increment()
            val data = apiService.getListMovieDiscover()
            val dataArray = data.results

            if (dataArray.isNullOrEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(dataArray.subList(0, 5)))
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.onCompletion {
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }

    fun getAllListNowPlayingMovie(): DataSource.Factory<Int, MoviesItem> =
        object : DataSource.Factory<Int, MoviesItem>() {
            override fun create(): DataSource<Int, MoviesItem> {
                return object : MovieDataSource(coroutineScope) {
                    override val fetch: suspend (Int) -> MovieResponse
                        get() = { page ->
                            apiService.getListMovieNowPlaying(page = page)
                        }
                }
            }
        }

    fun getListMovieNowPlaying(): Flow<ApiResponse<List<MoviesItem>>> {
        return flow {
            EspressoIdlingResource.increment()
            val data = apiService.getListMovieNowPlaying()
            val dataArray = data.results

            if (dataArray.isNullOrEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(dataArray.subList(0, 10)))
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.onCompletion {
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }

    fun getAllListUpcomingMovie(): DataSource.Factory<Int, MoviesItem> =
        object : DataSource.Factory<Int, MoviesItem>() {
            override fun create(): DataSource<Int, MoviesItem> {
                return object : MovieDataSource(coroutineScope) {
                    override val fetch: suspend (Int) -> MovieResponse
                        get() = { page ->
                            apiService.getListMovieUpcoming(page = page)
                        }
                }
            }
        }

    fun getListMovieUpcoming(): Flow<ApiResponse<List<MoviesItem>>> {
        return flow {
            EspressoIdlingResource.increment()
            val data = apiService.getListMovieUpcoming()
            val dataArray = data.results

            if (dataArray.isNullOrEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(dataArray.subList(0, 10)))
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.onCompletion {
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }

    fun getAllListDiscoverTv(): DataSource.Factory<Int, TvShowItem> =
        object : DataSource.Factory<Int, TvShowItem>() {
            override fun create(): DataSource<Int, TvShowItem> {
                return object : TvDataSource(coroutineScope) {
                    override val fetch: suspend (Int) -> TvShowResponse
                        get() = { page ->
                            apiService.getListTvDiscover(page = page)
                        }
                }
            }
        }

    fun getListTvDiscover(): Flow<ApiResponse<List<TvShowItem>>> {
        return flow {
            EspressoIdlingResource.increment()
            val data = apiService.getListTvDiscover()
            val dataArray = data.results

            if (dataArray.isNullOrEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(dataArray.subList(0, 5)))
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.onCompletion {
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }

    fun getAllListAiringTodayTv(): DataSource.Factory<Int, TvShowItem> =
        object : DataSource.Factory<Int, TvShowItem>() {
            override fun create(): DataSource<Int, TvShowItem> {
                return object : TvDataSource(coroutineScope) {
                    override val fetch: suspend (Int) -> TvShowResponse
                        get() = { page ->
                            apiService.getListTvAiringToday(page = page)
                        }
                }
            }
        }

    fun getListTvAiringToday(): Flow<ApiResponse<List<TvShowItem>>> {
        return flow {
            EspressoIdlingResource.increment()
            val data = apiService.getListTvAiringToday()
            val dataArray = data.results

            if (dataArray.isNullOrEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(dataArray.subList(0, 10)))
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.onCompletion {
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }

    fun getAllListOnTheAirTv(): DataSource.Factory<Int, TvShowItem> =
        object : DataSource.Factory<Int, TvShowItem>() {
            override fun create(): DataSource<Int, TvShowItem> {
                return object : TvDataSource(coroutineScope) {
                    override val fetch: suspend (Int) -> TvShowResponse
                        get() = { page ->
                            apiService.getListTvOnTheAir(page = page)
                        }
                }
            }
        }

    fun getListTvOnTheAir(): Flow<ApiResponse<List<TvShowItem>>> {
        return flow {
            EspressoIdlingResource.increment()
            val data = apiService.getListTvOnTheAir()
            val dataArray = data.results

            if (dataArray.isNullOrEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(dataArray.subList(0, 10)))
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.onCompletion {
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailMovie(id: String): Flow<ApiResponse<MovieDetailResponse>> {
        return flow {
            EspressoIdlingResource.increment()
            val data = apiService.getDetailMovie(id)
            if (data.id != null) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }.onCompletion {
            EspressoIdlingResource.decrement()
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.flowOn(Dispatchers.IO)
    }

    fun getDetailTv(id: String): Flow<ApiResponse<TvDetailResponse>> {
        return flow {
            EspressoIdlingResource.increment()
            val data = apiService.getDetailTv(id)
            if (data.success) {
                emit(ApiResponse.Success(data))
            } else {
                emit(ApiResponse.Empty)
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.onCompletion {
            EspressoIdlingResource.decrement()
        }.flowOn(Dispatchers.IO)
    }
}