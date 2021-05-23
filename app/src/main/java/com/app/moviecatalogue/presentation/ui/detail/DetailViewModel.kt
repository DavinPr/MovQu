package com.app.moviecatalogue.presentation.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moviecatalogue.core.domain.usecase.AppUseCase
import com.moviecatalogue.core.domain.usecase.model.MovieDetail
import com.moviecatalogue.core.domain.usecase.model.TvDetail

class DetailViewModel(private val useCase: AppUseCase) : ViewModel() {
    fun getDetailMovie(id: String) = useCase.getDetailMovie(id).asLiveData()
    fun getDetailTv(id: String) = useCase.getDetailTv(id).asLiveData()

    fun insertFavoriteFromMovie(detail: MovieDetail) = useCase.insertFavoriteFromMovie(detail)
    fun insertFavoriteFromTv(detail: TvDetail) = useCase.insertFavoriteFromTv(detail)

    fun deleteFavoriteFromMovie(detail: MovieDetail) = useCase.deleteFavoriteFromMovie(detail)
    fun deleteFavoriteFromTv(detail: TvDetail) = useCase.deleteFavoriteFromTv(detail)

    fun checkFavorite(id: String) = useCase.isFavorited(id).asLiveData()
}