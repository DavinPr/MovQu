package com.app.moviecatalogue.presentation.ui.home.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.moviecatalogue.core.data.Resource
import com.moviecatalogue.core.domain.usecase.AppInteractor
import com.app.moviecatalogue.presentation.ui.home.fragment.movie.MovieViewModel
import com.app.moviecatalogue.utils.DataDummy
import com.app.moviecatalogue.utils.getValueOrAwait
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private val useCase = mock(AppInteractor::class.java)

    @Before
    fun setup() {
        val dummyMovie = Resource.Success(DataDummy.generateDummyMovie())
        val movie = flow {
            emit(dummyMovie)
        }
        `when`(useCase.getListMovieDiscover()).thenReturn(movie)
        `when`(useCase.getListMovieNowPlaying()).thenReturn(movie)
        `when`(useCase.getListMovieUpcoming()).thenReturn(movie)

        viewModel = MovieViewModel(useCase)
    }

    @Test
    fun `Test data discover not null and equal`() {
        val movieData = viewModel.getDiscover.getValueOrAwait().data
        verify(useCase).getListMovieDiscover()
        assertNotNull(movieData)
        assertEquals(3, movieData?.size)
    }

    @Test
    fun `Test data nowplaying not null and equal`() {
        val movieData = viewModel.getDiscover.getValueOrAwait().data
        verify(useCase).getListMovieNowPlaying()
        assertNotNull(movieData)
        assertEquals(3, movieData?.size)
    }

    @Test
    fun `Test data upcoming not null and equal`() {
        val movieData = viewModel.getDiscover.getValueOrAwait().data
        verify(useCase).getListMovieUpcoming()
        assertNotNull(movieData)
        assertEquals(3, movieData?.size)
    }
}