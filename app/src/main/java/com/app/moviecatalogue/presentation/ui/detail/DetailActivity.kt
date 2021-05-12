package com.app.moviecatalogue.presentation.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.app.moviecatalogue.R
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.domain.usecase.model.MovieDetail
import com.app.moviecatalogue.core.domain.usecase.model.MovieGenre
import com.app.moviecatalogue.core.domain.usecase.model.TvDetail
import com.app.moviecatalogue.core.domain.usecase.model.TvGenre
import com.app.moviecatalogue.databinding.ActivityDetailBinding
import com.app.moviecatalogue.presentation.ui.detail.adapter.GenreListAdapter
import com.app.moviecatalogue.Constants.ID_KEY
import com.app.moviecatalogue.Constants.MOVIE_TYPE
import com.app.moviecatalogue.Constants.TV_TYPE
import com.app.moviecatalogue.Constants.TYPE_KEY
import com.app.moviecatalogue.presentation.utils.dateFormat
import com.app.moviecatalogue.presentation.utils.runtimeFormat
import com.app.moviecatalogue.presentation.utils.toImageurl
import com.app.moviecatalogue.presentation.utils.toStringList
import com.bumptech.glide.Glide
import com.google.android.flexbox.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()
    private var isFavorite = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getIntExtra(TYPE_KEY, 101)
        val id = intent.getStringExtra(ID_KEY)
        if (id != null) {
            viewModel.checkFavorite(id).observe(this) {
                isFavorite = it
                setFavorite(it)
            }
        }
        when (type) {
            MOVIE_TYPE -> {
                binding.detailMovieAttribute.apply {
                    root.visibility = View.VISIBLE
                }
                if (id != null) setDetailMovieView(id)
            }
            TV_TYPE -> {
                binding.detailTvAttribute.apply {
                    root.visibility = View.VISIBLE
                }
                if (id != null) setDetailTvView(id)
            }
        }
    }

    private fun setDetailMovieView(id: String) {
        binding.toolbar.activityTitle.text = resources.getString(R.string.movie)
        viewModel.getDetailMovie(id).observe(this) { detail ->
            when (detail) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    val data = detail.data
                    if (data != null) {
                        setDataMovie(data)
                        binding.toolbar.btnFavorite.setOnClickListener {
                            setFavorite(!isFavorite)
                            if (isFavorite) {
                                viewModel.deleteFavoriteFromMovie(data)
                                return@setOnClickListener
                            }
                            viewModel.insertFavoriteFromMovie(data)
                        }
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                }
            }
        }
    }

    private fun setDetailTvView(id: String) {
        binding.toolbar.activityTitle.text = resources.getString(R.string.tvshow)
        viewModel.getDetailTv(id).observe(this) { detail ->
            when (detail) {
                is Resource.Loading -> {
                    showLoading(true)
                }
                is Resource.Success -> {
                    showLoading(false)
                    val data = detail.data
                    if (data != null) {
                        setDataTv(data)
                        binding.toolbar.btnFavorite.setOnClickListener {
                            setFavorite(!isFavorite)
                            if (isFavorite) {
                                viewModel.deleteFavoriteFromTv(data)
                                return@setOnClickListener
                            }
                            viewModel.insertFavoriteFromTv(data)
                        }
                    }
                }
                is Resource.Error -> {
                    showLoading(false)
                }
            }
        }
    }

    private fun setDataMovie(detail: MovieDetail) {
        binding.detailMovieAttribute.apply {
            Glide.with(this@DetailActivity)
                .load(detail.poster_path?.toImageurl())
                .into(posterMovie)

            titleMovie.text = detail.title
            dateMovie.text = detail.release_date.dateFormat(this@DetailActivity)
            runtimeMovie.text = detail.runtime?.runtimeFormat()
            ratingMovie.text = detail.vote_average.toString()
            overviewMovie.text = detail.overview
            taglineMovie.text = detail.tagline

            val genreAdapter = GenreListAdapter<MovieGenre>()
            detail.genres?.let { genreAdapter.setGenre(it) }
            rvGenreMovie.apply {
                adapter = genreAdapter
                val flexboxLm = FlexboxLayoutManager(this@DetailActivity)
                flexboxLm.alignItems = AlignItems.STRETCH
                flexboxLm.flexWrap = FlexWrap.WRAP
                layoutManager = flexboxLm
                hasFixedSize()
            }
        }
    }

    private fun setDataTv(detail: TvDetail) {
        binding.detailTvAttribute.apply {
            Glide.with(this@DetailActivity)
                .load(detail.posterPath?.toImageurl())
                .into(posterTv)

            titleTv.text = detail.name
            firstDateTv.text = detail.firstAirDate?.dateFormat(this@DetailActivity) ?: "-"
            lastDateTv.text = detail.lastAirDate?.dateFormat(this@DetailActivity) ?: "-"
            ratingTv.text = detail.voteAverage.toString()
            overviewTv.text = detail.overview
            episodes.text = detail.numberOfEpisodes?.toString() ?: "-"
            seasons.text = detail.numberOfSeasons?.toString() ?: "-"

            detail.episodeRunTime?.let {
                if (it.isNotEmpty()) runtimeTv.text = it.toStringList()
            }
            taglineTv.text = detail.tagline ?: resources.getString(R.string.no_tagline)

            val genreAdapter = GenreListAdapter<TvGenre>()
            detail.genres?.let { genreAdapter.setGenre(it) }
            rvGenreTv.apply {
                adapter = genreAdapter
                val flexboxLm = FlexboxLayoutManager(this@DetailActivity)
                flexboxLm.alignItems = AlignItems.STRETCH
                flexboxLm.flexWrap = FlexWrap.WRAP
                layoutManager = flexboxLm
                hasFixedSize()
            }
        }
    }

    private fun setFavorite(state: Boolean) {
        if (state) {
            binding.toolbar.btnFavorite.apply {
                Glide.with(this@DetailActivity)
                    .load(R.drawable.ic_love_filled)
                    .into(this)
                imageTintList = ContextCompat.getColorStateList(this@DetailActivity, R.color.red)
            }

        } else {
            binding.toolbar.btnFavorite.apply {
                Glide.with(this@DetailActivity)
                    .load(R.drawable.ic_love_border)
                    .into(this)
                imageTintList = ContextCompat.getColorStateList(this@DetailActivity, R.color.black)
            }
        }
    }

    private fun showLoading(state: Boolean) {
        binding.detailLoading.progressBar.isVisible = state
    }
}