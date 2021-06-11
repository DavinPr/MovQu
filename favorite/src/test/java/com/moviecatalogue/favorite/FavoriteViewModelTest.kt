package com.moviecatalogue.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.moviecatalogue.core.domain.usecase.AppInteractor
import com.moviecatalogue.core.domain.usecase.model.Favorite
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@FlowPreview
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class FavoriteViewModelTest {

    private lateinit var viewModel: FavoriteViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val useCase = Mockito.mock(AppInteractor::class.java)

    @Mock
    private lateinit var observer: Observer<PagedList<Favorite>>

    @Before
    fun setup() {
        viewModel = FavoriteViewModel(useCase)
    }

    @Test
    fun `Test data movie favorite not null and equal`() {

        val expected = MutableLiveData<PagedList<Favorite>>()
        expected.value = PagedTestDataSources.snapshot(DataDummy.generateDummyFavorite())
        `when`(useCase.getAllFavorite()).thenReturn(expected)

        runBlocking {
            viewModel.queryChannel.send("all")
        }
        viewModel.getListResult.observeForever {
            it.observeForever(observer)
        }
        verify(observer).onChanged(expected.value)

        val expectedValue = expected.value
        val actualValue = viewModel.getListResult.getValueOrAwait().value
        assertEquals(expectedValue, actualValue)
        assertEquals(expectedValue?.snapshot(), actualValue?.snapshot())
        assertEquals(expectedValue?.size, actualValue?.size)
    }

    class PagedTestDataSources private constructor(private val items: List<Favorite>) :
        PositionalDataSource<Favorite>() {
        companion object {
            fun snapshot(items: List<Favorite> = listOf()): PagedList<Favorite> {
                return PagedList.Builder(PagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(
            params: LoadInitialParams,
            callback: LoadInitialCallback<Favorite>
        ) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Favorite>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }
}