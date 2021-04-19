package com.app.moviecatalogue.core.data.remote

import com.app.moviecatalogue.core.data.remote.network.ApiResponse
import com.app.moviecatalogue.core.data.remote.network.ApiService
import com.app.moviecatalogue.core.data.remote.response.MovieDetailResponse
import com.app.moviecatalogue.core.data.remote.response.MoviesItem
import com.app.moviecatalogue.core.data.remote.response.TvDetailResponse
import com.app.moviecatalogue.core.data.remote.response.TvShowItem
import com.app.moviecatalogue.presentation.utils.EspressoIdlingResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class RemoteDataSource(private val apiService: ApiService) {

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
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.onCompletion {
            EspressoIdlingResource.decrement()
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