package com.app.moviecatalogue.ui.home.fragment.movie

import androidx.lifecycle.ViewModel
import com.app.moviecatalogue.utils.DataDummy

class MovieViewModel : ViewModel() {
    val getDiscover = DataDummy.generateDummyMovie().subList(0, 5)
    val getNowPlaying = DataDummy.generateDummyMovie()
    val getUpcoming = DataDummy.generateDummyMovie()

}