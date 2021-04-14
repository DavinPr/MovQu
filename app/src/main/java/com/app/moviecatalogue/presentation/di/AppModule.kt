package com.app.moviecatalogue.presentation.di

import com.app.moviecatalogue.core.domain.usecase.AppInteractor
import com.app.moviecatalogue.core.domain.usecase.AppUseCase
import com.app.moviecatalogue.presentation.ui.detail.DetailViewModel
import com.app.moviecatalogue.presentation.ui.home.fragment.movie.MovieViewModel
import com.app.moviecatalogue.presentation.ui.home.fragment.tvshow.TvShowViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AppUseCase> { AppInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}