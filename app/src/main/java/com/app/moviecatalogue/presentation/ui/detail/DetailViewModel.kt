package com.app.moviecatalogue.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.app.moviecatalogue.core.domain.usecase.AppUseCase

class DetailViewModel(private val useCase: AppUseCase) : ViewModel() {
    fun getDetailMovie(id: String) = useCase.getDetailMovie(id).asLiveData()
    fun getDetailTv(id: String) = useCase.getDetailTv(id).asLiveData()
}