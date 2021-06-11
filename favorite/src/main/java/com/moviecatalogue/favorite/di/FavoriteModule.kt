package com.moviecatalogue.favorite.di

import com.moviecatalogue.favorite.FavoriteViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalCoroutinesApi
@FlowPreview
val favoriteModule = module {
    viewModel { FavoriteViewModel(get()) }
}