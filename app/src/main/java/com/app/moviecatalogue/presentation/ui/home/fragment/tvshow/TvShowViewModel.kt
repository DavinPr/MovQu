package com.app.moviecatalogue.presentation.ui.home.fragment.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.moviecatalogue.core.domain.usecase.AppUseCase

class TvShowViewModel(useCase: AppUseCase) : ViewModel() {
    val getTvDiscover = useCase.getListTvDiscover().asLiveData()
    val getTvAiringToday = useCase.getListTvAiringToday().asLiveData()
    val getTvOnTheAir = useCase.getListTvOnTheAir().asLiveData()
}