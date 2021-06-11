package com.app.moviecatalogue.presentation.di

import com.app.moviecatalogue.presentation.ui.allfilm.AllFilmViewModel
import com.app.moviecatalogue.presentation.ui.detail.DetailViewModel
import com.app.moviecatalogue.presentation.ui.home.fragment.movie.MovieViewModel
import com.app.moviecatalogue.presentation.ui.home.fragment.tvshow.TvShowViewModel
import com.moviecatalogue.core.domain.usecase.AppInteractor
import com.moviecatalogue.core.domain.usecase.AppUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<AppUseCase> { AppInteractor(get()) }
}

@ExperimentalCoroutinesApi
@FlowPreview
val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailViewModel(get()) }
    viewModel { AllFilmViewModel(get()) }
}