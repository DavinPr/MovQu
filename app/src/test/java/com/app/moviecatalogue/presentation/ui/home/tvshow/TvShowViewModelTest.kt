package com.app.moviecatalogue.presentation.ui.home.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.domain.usecase.AppInteractor
import com.app.moviecatalogue.utils.getValueOrAwait
import com.app.moviecatalogue.presentation.ui.home.fragment.tvshow.TvShowViewModel
import com.app.moviecatalogue.presentation.utils.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val useCase = mock(AppInteractor::class.java)

    @Before
    fun setup() {
        val dummyTv = Resource.Success(DataDummy.generateDummyTv())
        val tv = flow {
            emit(dummyTv)
        }
        Mockito.`when`(useCase.getListTvDiscover()).thenReturn(tv)
        Mockito.`when`(useCase.getListTvAiringToday()).thenReturn(tv)
        Mockito.`when`(useCase.getListTvOnTheAir()).thenReturn(tv)

        viewModel = TvShowViewModel(useCase)
    }

    @Test
    fun `Test data discover not null and equal`() {
        val data = viewModel.getTvDiscover.getValueOrAwait().data
        verify(useCase).getListTvDiscover()
        Assert.assertNotNull(data)
        Assert.assertEquals(3, data?.size)
    }

    @Test
    fun `Test data airingToday not null and equal`() {
        val data = viewModel.getTvAiringToday.getValueOrAwait().data
        verify(useCase).getListTvAiringToday()
        Assert.assertNotNull(data)
        Assert.assertEquals(3, data?.size)
    }

    @Test
    fun `Test data onTheAir not null and equal`() {
        val data = viewModel.getTvOnTheAir.getValueOrAwait().data
        verify(useCase).getListTvOnTheAir()
        Assert.assertNotNull(data)
        Assert.assertEquals(3, data?.size)
    }
}