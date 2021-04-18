package com.app.moviecatalogue.presentation.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.domain.usecase.AppInteractor
import com.app.moviecatalogue.utils.getValueOrAwait
import com.app.moviecatalogue.presentation.utils.DataDummy
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
        `when`(useCase.getDetailMovie("412656")).thenReturn(detailMovie)

        val detailTv = flow {
            emit(dataDummyTv)
        }
        `when`(useCase.getDetailTv("88396")).thenReturn(detailTv)

        viewModel = DetailViewModel(useCase)
    }

    @Test
    fun `Test detail movie not null and equal`() {
        val id = "412656"
        val data = viewModel.getDetailMovie(id).getValueOrAwait().data
        verify(useCase).getDetailMovie(id)
        assertNotNull(data)
        assertEquals(id, data?.id.toString())
        assertEquals("Godzilla vs Kong", data?.title)
    }

    @Test
    fun `Test detail tv not null and equal`() {
        val id = "88396"
        val data = viewModel.getDetailTv(id).getValueOrAwait().data
        verify(useCase).getDetailTv(id)
        assertNotNull(data)
        assertEquals("The Falcon and the Winter Soldier", data?.name)
    }
}