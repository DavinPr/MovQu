package com.app.moviecatalogue.core.data.remote.paging

import androidx.paging.PageKeyedDataSource
import com.app.moviecatalogue.Constants.FIRST_PAGE
import com.app.moviecatalogue.core.data.remote.response.TvShowItem
import com.app.moviecatalogue.core.data.remote.response.TvShowResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class TvDataSource(private val coroutineScope: CoroutineScope) :
    PageKeyedDataSource<Int, TvShowItem>() {

    private var page = FIRST_PAGE

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TvShowItem>
    ) {
        coroutineScope.launch {
            callback.onResult(fetch(page).results ?: listOf(), null, page + 1)
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TvShowItem>) {
        coroutineScope.launch {
            if (fetch(params.key).totalPages >= params.key) {
                callback.onResult(fetch(params.key).results ?: listOf(), params.key + 1)
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TvShowItem>) {

    }

    abstract val fetch: suspend (Int) -> TvShowResponse

}