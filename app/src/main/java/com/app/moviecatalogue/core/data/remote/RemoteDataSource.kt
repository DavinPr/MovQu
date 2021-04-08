package com.app.moviecatalogue.core.data.remote

import com.app.moviecatalogue.core.data.remote.network.ApiResponse
import com.app.moviecatalogue.core.data.remote.network.ApiService
import com.app.moviecatalogue.core.data.remote.response.MoviesItem
import com.app.moviecatalogue.core.data.remote.response.TvShowItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    fun getListDiscoverMovie(): Flow<ApiResponse<List<MoviesItem>>> =
        flow {
            val data = apiService.getListMovieDiscover()
            val dataArray = data.results

            if (dataArray.isNullOrEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(dataArray))
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.flowOn(Dispatchers.IO)

    fun getListMovieNowPlaying(): Flow<ApiResponse<List<MoviesItem>>> =
        flow {
            val data = apiService.getListMovieNowPlaying()
            val dataArray = data.results

            if (dataArray.isNullOrEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(dataArray))
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.flowOn(Dispatchers.IO)

    fun getListMovieUpcoming(): Flow<ApiResponse<List<MoviesItem>>> =
        flow {
            val data = apiService.getListMovieUpcoming()
            val dataArray = data.results

            if (dataArray.isNullOrEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(dataArray))
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.flowOn(Dispatchers.IO)

    fun getListTvDiscover(): Flow<ApiResponse<List<TvShowItem>>> =
        flow {
            val data = apiService.getListTvDiscover()
            val dataArray = data.results

            if (dataArray.isNullOrEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(dataArray))
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.flowOn(Dispatchers.IO)

    fun getListTvAiringToday(): Flow<ApiResponse<List<TvShowItem>>> =
        flow {
            val data = apiService.getListTvAiringToday()
            val dataArray = data.results

            if (dataArray.isNullOrEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(dataArray))
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.flowOn(Dispatchers.IO)

    fun getListTvOnTheAir(): Flow<ApiResponse<List<TvShowItem>>> =
        flow {
            val data = apiService.getListTvOnTheAir()
            val dataArray = data.results

            if (dataArray.isNullOrEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                emit(ApiResponse.Success(dataArray))
            }
        }.catch { e ->
            emit(ApiResponse.Error(e.toString()))
        }.flowOn(Dispatchers.IO)
}