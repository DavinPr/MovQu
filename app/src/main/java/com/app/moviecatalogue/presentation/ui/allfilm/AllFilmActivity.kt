package com.app.moviecatalogue.presentation.ui.allfilm

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import androidx.recyclerview.widget.GridLayoutManager
import com.app.moviecatalogue.Constants.ID_KEY
import com.app.moviecatalogue.Constants.LIST_TYPE
import com.app.moviecatalogue.Constants.MOVIE_DISCOVER_TYPE
import com.app.moviecatalogue.Constants.MOVIE_NOW_PLAYING_TYPE
import com.app.moviecatalogue.Constants.MOVIE_TYPE
import com.app.moviecatalogue.Constants.MOVIE_UPCOMING_TYPE
import com.app.moviecatalogue.Constants.TV_AIRING_TODAY_TYPE
import com.app.moviecatalogue.Constants.TV_DISCOVER_TYPE
import com.app.moviecatalogue.Constants.TV_ON_THE_AIR_TYPE
import com.app.moviecatalogue.Constants.TV_TYPE
import com.app.moviecatalogue.Constants.TYPE_KEY
import com.app.moviecatalogue.R
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.core.domain.usecase.model.TvShow
import com.app.moviecatalogue.databinding.ActivityAllFilmBinding
import com.app.moviecatalogue.presentation.ui.detail.DetailActivity
import com.app.moviecatalogue.presentation.ui.home.custom.customdecoration.GridSpacesItemDecoration
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import kotlin.math.roundToInt

class AllFilmActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllFilmBinding
    private val viewModel: AllFilmViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val filmType = intent.getIntExtra(TYPE_KEY, 0)
        val listType = intent.getIntExtra(LIST_TYPE, 0)

        when (filmType) {
            MOVIE_TYPE -> {
                val movieAdapter = AllMovieListAdapter()

                val liveDataItem: LiveData<Resource<PagedList<Movie>>>? = when (listType) {
                    MOVIE_DISCOVER_TYPE -> {
                        binding.toolbar.listTitle.text =
                            resources.getString(R.string.discover_movie)
                        viewModel.getAllMovieDiscover()
                    }
                    MOVIE_NOW_PLAYING_TYPE -> {
                        binding.toolbar.listTitle.text =
                            resources.getString(R.string.now_playing_movie)
                        viewModel.getAllMovieNowPlaying()
                    }
                    MOVIE_UPCOMING_TYPE -> {
                        binding.toolbar.listTitle.text =
                            resources.getString(R.string.upcoming_movie)
                        viewModel.getAllMovieUpcoming()
                    }
                    else -> null
                }
                liveDataItem?.observe(this) { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            binding.allFilmLoading.root.visibility = View.GONE
                            binding.rvFilm.visibility = View.VISIBLE
                            val data = resource.data
                            movieAdapter.submitList(data)
                        }
                        is Resource.Loading -> {
                            binding.allFilmLoading.root.visibility = View.VISIBLE
                            binding.rvFilm.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            Timber.d("Error")
                        }
                    }
                }
                movieAdapter.onClick = { movie ->
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(ID_KEY, movie.id)
                    intent.putExtra(TYPE_KEY, MOVIE_TYPE)
                    startActivity(intent)
                }
                binding.rvFilm.adapter = movieAdapter
            }
            TV_TYPE -> {
                val tvAdapter = AllTvShowListAdapter()

                val liveDataItem: LiveData<Resource<PagedList<TvShow>>>? = when (listType) {
                    TV_DISCOVER_TYPE -> {
                        binding.toolbar.listTitle.text =
                            resources.getString(R.string.discover_tv)
                        viewModel.getAllTvDiscover()
                    }
                    TV_AIRING_TODAY_TYPE -> {
                        binding.toolbar.listTitle.text =
                            resources.getString(R.string.airing_today)
                        viewModel.getAllTvAiringToday()
                    }
                    TV_ON_THE_AIR_TYPE -> {
                        binding.toolbar.listTitle.text = resources.getString(R.string.on_the_air)
                        viewModel.getAllTvOnTheAir()
                    }
                    else -> null
                }
                liveDataItem?.observe(this) { resource ->
                    when (resource) {
                        is Resource.Success -> {
                            binding.allFilmLoading.root.visibility = View.GONE
                            binding.rvFilm.visibility = View.VISIBLE
                            val data = resource.data
                            tvAdapter.submitList(data)
                        }
                        is Resource.Loading -> {
                            binding.allFilmLoading.root.visibility = View.VISIBLE
                            binding.rvFilm.visibility = View.GONE
                        }
                        is Resource.Error -> {
                            Timber.d("Error")
                        }
                    }
                }
                tvAdapter.onClick = { tv ->
                    val intent = Intent(this, DetailActivity::class.java)
                    intent.putExtra(ID_KEY, tv.id)
                    intent.putExtra(TYPE_KEY, TV_TYPE)
                    startActivity(intent)
                }
                binding.rvFilm.adapter = tvAdapter
            }
        }

        binding.rvFilm.apply {
            val spanCount = 3
            val spacing = (4 * resources.displayMetrics.density).roundToInt()

            layoutManager = GridLayoutManager(this@AllFilmActivity, 3)
            hasFixedSize()
            addItemDecoration(GridSpacesItemDecoration(spanCount, spacing, true))
        }
    }
}