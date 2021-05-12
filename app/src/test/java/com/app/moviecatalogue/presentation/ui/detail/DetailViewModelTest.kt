package com.app.moviecatalogue.presentation.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.domain.usecase.AppInteractor
import com.app.moviecatalogue.presentation.utils.DataDummy
import com.app.moviecatalogue.utils.getValueOrAwait
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var useCase: AppInteractor

    @Before
    fun setup() {
        val dataDummyMovie = Resource.Success(DataDummy.generateDummyDetailMovie())
        val dataDummyTv = Resource.Success(DataDummy.generateDummyDetailTv())
        val detailMovie = flow {
            emit(dataDummyMovie)
        }
        `when`(useCase.getDetailMovie(DataDummy.generateDummyDetailMovie().id.toString())).thenReturn(
            detailMovie
        )

        val detailTv = flow {
            emit(dataDummyTv)
        }
        `when`(useCase.getDetailTv(DataDummy.generateDummyDetailTv().id.toString())).thenReturn(detailTv)

        viewModel = DetailViewModel(useCase)
    }

    @Test
    fun `Test detail movie not null and equal`() {
        val id = DataDummy.generateDummyDetailMovie().id.toString()
        val title = DataDummy.generateDummyDetailMovie().title
        val data = viewModel.getDetailMovie(id).getValueOrAwait().data
        verify(useCase).getDetailMovie(id)
        assertNotNull(data)
        assertEquals(id, data?.id.toString())
        assertEquals(title, data?.title)
    }

    @Test
    fun `Test detail tv not null and equal`() {
        val id = DataDummy.generateDummyDetailTv().id.toString()
        val title = DataDummy.generateDummyDetailTv().name
        val data = viewModel.getDetailTv(id).getValueOrAwait().data
        verify(useCase).getDetailTv(id)
        assertNotNull(data)
        assertEquals(title, data?.name)
    }

    @Test
    fun insertFavoriteFromMovie() {
        val detail = DataDummy.generateDummyDetailMovie()
        doNothing().`when`(useCase).insertFavoriteFromMovie(detail)
        useCase.insertFavoriteFromMovie(detail)

        verify(useCase, Mockito.times(1)).insertFavoriteFromMovie(detail)
    }

    @Test
    fun insertFavoriteFromTv() {
        val detail = DataDummy.generateDummyDetailTv()
        doNothing().`when`(useCase).insertFavoriteFromTv(detail)
        useCase.insertFavoriteFromTv(detail)

        verify(useCase, Mockito.times(1)).insertFavoriteFromTv(detail)
    }

    @Test
    fun deleteFavoriteFromMovie() {
        val detail = DataDummy.generateDummyDetailMovie()
        doNothing().`when`(useCase).deleteFavoriteFromMovie(detail)
        useCase.deleteFavoriteFromMovie(detail)

        verify(useCase, Mockito.times(1)).deleteFavoriteFromMovie(detail)
    }

    @Test
    fun deleteFavoriteFromTv() {
        val detail = DataDummy.generateDummyDetailTv()
        doNothing().`when`(useCase).deleteFavoriteFromTv(detail)
        useCase.deleteFavoriteFromTv(detail)

        verify(useCase, Mockito.times(1)).deleteFavoriteFromTv(detail)
    }
}