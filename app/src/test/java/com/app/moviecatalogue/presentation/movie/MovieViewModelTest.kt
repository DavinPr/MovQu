package com.app.moviecatalogue.presentation.movie

import com.app.moviecatalogue.ui.home.fragment.movie.MovieViewModel
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setup() {

        viewModel = MovieViewModel()
    }

    @Test
    fun `Test data discover not null and equal`() {
        val movieData = viewModel.getDiscover
        assertNotNull(movieData)
        assertEquals(5, movieData.size)
    }

    @Test
    fun `Test data nowplaying not null and equal`() {
        val movieData = viewModel.getNowPlaying
        assertNotNull(movieData)
        assertEquals(10, movieData.size)
    }

    @Test
    fun `Test data upcoming not null and equal`() {
        val movieData = viewModel.getUpcoming
        assertNotNull(movieData)
        assertEquals(10, movieData.size)
    }
}