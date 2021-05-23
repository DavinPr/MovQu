package com.app.moviecatalogue.presentation.ui.home.fragment.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.moviecatalogue.core.domain.usecase.AppUseCase
import com.app.moviecatalogue.presentation.ui.home.fragment.favorite.category.Category
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest

@FlowPreview
@ExperimentalCoroutinesApi
class FavoriteViewModel(private val useCase: AppUseCase) : ViewModel() {

    val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)
    val getListResult = queryChannel.asFlow()
        .distinctUntilChanged()
        .filter { it.trim().isNotEmpty() }
        .mapLatest {
            if (it == "all"){
                useCase.getAllFavorite()
            }else{
                useCase.getFavoriteByType(it)
            }
        }.asLiveData()

    fun generateCategoryItem(): List<Category> {
        val list = ArrayList<Category>()
        list.add(Category("All", "all"))
        list.add(Category("Movies", "movie"))
        list.add(Category("Tv Shows", "tv"))
        return list
    }
}