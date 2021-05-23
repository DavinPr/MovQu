package com.app.moviecatalogue.presentation.ui.home.fragment.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moviecatalogue.core.domain.usecase.AppUseCase

class MovieViewModel(useCase: AppUseCase) : ViewModel() {
    val getDiscover = useCase.getListMovieDiscover().asLiveData()
    val getNowPlaying = useCase.getListMovieNowPlaying().asLiveData()
    val getUpcoming = useCase.getListMovieUpcoming().asLiveData()
}