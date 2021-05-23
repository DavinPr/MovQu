package com.app.moviecatalogue.presentation.ui.allfilm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moviecatalogue.core.domain.usecase.AppUseCase

class AllFilmViewModel(private val appUseCase: AppUseCase) : ViewModel() {
    fun getAllMovieDiscover() = appUseCase.getAllListMovieDiscover().asLiveData()
    fun getAllMovieUpcoming() = appUseCase.getAllListMovieUpcoming().asLiveData()
    fun getAllMovieNowPlaying() = appUseCase.getAllListMovieNowPlaying().asLiveData()

    fun getAllTvDiscover() = appUseCase.getAllListTvDiscover().asLiveData()
    fun getAllTvAiringToday() = appUseCase.getAllListTvAiringToday().asLiveData()
    fun getAllTvOnTheAir() = appUseCase.getAllListTvOnTheAir().asLiveData()
}