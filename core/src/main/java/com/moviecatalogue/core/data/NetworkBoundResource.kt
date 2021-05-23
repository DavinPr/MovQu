package com.moviecatalogue.core.data

import com.moviecatalogue.core.data.remote.network.ApiResponse
import com.moviecatalogue.core.utils.EspressoIdlingResource
import kotlinx.coroutines.flow.*
import timber.log.Timber

inline fun <DB, REMOTE> networkBoundResource(
    crossinline query: () -> Flow<DB>,
    crossinline fetch: suspend () -> Flow<ApiResponse<REMOTE>>,
    crossinline saveFetchResult: suspend (REMOTE) -> Unit,
    crossinline shouldFetch: (DB) -> Boolean = { true }
) = flow {
    EspressoIdlingResource.increment()
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

    EspressoIdlingResource.decrement()
    emitAll(flow)
}.onCompletion {
    Timber.d("Complete")
}