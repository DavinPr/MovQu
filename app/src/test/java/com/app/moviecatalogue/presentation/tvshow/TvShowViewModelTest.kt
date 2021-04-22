package com.app.moviecatalogue.presentation.tvshow

import com.app.moviecatalogue.ui.home.fragment.tvshow.TvShowViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setup() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun `Test data discover not null and equal`() {
        val data = viewModel.getTvDiscover
        Assert.assertNotNull(data)
        Assert.assertEquals(5, data.size)
    }

    @Test
    fun `Test data airingToday not null and equal`() {
        val data = viewModel.getTvAiringToday
        Assert.assertNotNull(data)
        Assert.assertEquals(10, data.size)
    }

    @Test
    fun `Test data onTheAir not null and equal`() {
        val data = viewModel.getTvOnTheAir
        Assert.assertNotNull(data)
        Assert.assertEquals(10, data.size)
    }
}