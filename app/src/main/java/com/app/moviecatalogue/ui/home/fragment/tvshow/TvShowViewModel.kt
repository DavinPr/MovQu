package com.app.moviecatalogue.ui.home.fragment.tvshow

import androidx.lifecycle.ViewModel
import com.app.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {
    val getTvDiscover = DataDummy.generateDummyTv().subList(0,5)
    val getTvAiringToday = DataDummy.generateDummyTv()
    val getTvOnTheAir = DataDummy.generateDummyTv()
}