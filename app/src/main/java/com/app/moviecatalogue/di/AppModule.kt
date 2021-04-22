package com.app.moviecatalogue.di

import com.app.moviecatalogue.ui.home.fragment.movie.MovieViewModel
import com.app.moviecatalogue.ui.home.fragment.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MovieViewModel() }
    viewModel { TvShowViewModel() }
}