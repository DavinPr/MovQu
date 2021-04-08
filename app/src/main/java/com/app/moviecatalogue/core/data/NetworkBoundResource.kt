package com.app.moviecatalogue.core.data

import com.app.moviecatalogue.core.data.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

inline fun <DB, REMOTE> networkBoundResource(
    crossinline query: () -> Flow<DB>,
    crossinline fetch: suspend () -> Flow<ApiResponse<REMOTE>>,
    crossinline saveFetchResult: suspend (REMOTE) -> Unit,
    crossinline shouldFetch: (DB) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow =
        if (shouldFetch(data)) {
            emit(Resource.Loading(data))
            when (val apiResponse = fetch().first()) {
                is ApiResponse.Success -> {
                    saveFetchResult(apiResponse.data)
                    query().map { Resource.Success(it) }
                }

                is ApiResponse.Empty -> {
                    query().map { Resource.Success(it) }
                }

                is ApiResponse.Error -> {
                    query().map { Resource.Error(apiResponse.errorMessage, it) }
                }
            }
        } else {
            query().map { Resource.Success(it) }
        }

    emitAll(flow)
}