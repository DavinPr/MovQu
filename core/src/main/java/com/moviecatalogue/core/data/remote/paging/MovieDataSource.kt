package com.moviecatalogue.core.data.remote.paging

import androidx.paging.PageKeyedDataSource
import com.moviecatalogue.core.data.remote.response.MovieResponse
import com.moviecatalogue.core.data.remote.response.MoviesItem
import com.moviecatalogue.core.utils.Constants.FIRST_PAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

abstract class MovieDataSource(private val coroutineScope: CoroutineScope) :
    PageKeyedDataSource<Int, MoviesItem>() {

    private var page = FIRST_PAGE

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, MoviesItem>
    ) {
        try {
            coroutineScope.launch {
                callback.onResult(fetch(page).results ?: listOf(), null, page + 1)
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, MoviesItem>) {
        try {
            coroutineScope.launch {
                if (fetch(params.key).totalPages >= params.key) {
                    callback.onResult(fetch(params.key).results ?: listOf(), params.key + 1)
                }
            }
        } catch (e: Exception) {
            Timber.e(e)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, MoviesItem>) {

    }

    abstract val fetch: suspend (Int) -> MovieResponse

}