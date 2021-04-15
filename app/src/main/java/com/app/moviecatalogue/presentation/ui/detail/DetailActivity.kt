package com.app.moviecatalogue.presentation.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.app.moviecatalogue.R
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.domain.usecase.model.MovieDetail
import com.app.moviecatalogue.core.domain.usecase.model.MovieGenre
import com.app.moviecatalogue.core.domain.usecase.model.TvDetail
import com.app.moviecatalogue.core.domain.usecase.model.TvGenre
import com.app.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.app.moviecatalogue.databinding.ActivityDetailTvBinding
import com.app.moviecatalogue.presentation.ui.detail.adapter.GenreListAdapter
import com.app.moviecatalogue.presentation.utils.dateFormat
import com.app.moviecatalogue.presentation.utils.runtimeFormat
import com.app.moviecatalogue.presentation.utils.toImageurl
import com.bumptech.glide.Glide
import com.google.android.flexbox.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private lateinit var bindingMovie: ActivityDetailMovieBinding
    private lateinit var bindingTv: ActivityDetailTvBinding
    private val viewModel: DetailViewModel by viewModel()

    companion object {
        const val ID_KEY = "id_key"
        const val TYPE_KEY = "type_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val type = intent.getIntExtra(TYPE_KEY, 101)
        when (type) {
            101 -> {
                bindingMovie = ActivityDetailMovieBinding.inflate(layoutInflater)
                setContentView(bindingMovie.root)
            }
            102 -> {
                bindingTv = ActivityDetailTvBinding.inflate(layoutInflater)
                setContentView(bindingTv.root)
            }
        }

        val id = intent.getStringExtra(ID_KEY)

        if (id != null) {
            when (type) {
                101 -> setDetailMovieView(id)
                102 -> setDetailTvView(id)
            }
        }
    }

    private fun setDetailMovieView(id: String) {
        bindingMovie.toolbar.activityTitle.text = resources.getString(R.string.movie)
        viewModel.getDetailMovie(id).observe(this) { detail ->
            when (detail) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val data = detail.data
                    if (data != null) {
                        setDataMovie(data)
                    }
                }
                is Resource.Error -> {
                }
            }
        }
    }

    private fun setDetailTvView(id: String) {
        bindingTv.toolbar.activityTitle.text = resources.getString(R.string.tvshow)
        viewModel.getDetailTv(id).observe(this) { detail ->
            when (detail) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val data = detail.data
                    if (data != null) {
                        setDataTv(data)
                    }
                }
                is Resource.Error -> {
                }
            }
        }
    }

    private fun setDataMovie(detail: MovieDetail) {
        bindingMovie.detailFilmAttribute.apply {
            Glide.with(this@DetailActivity)
                .load(detail.poster_path?.toImageurl())
                .into(poster)

            title.text = detail.title
            date.text = detail.release_date.dateFormat(this@DetailActivity)
            runtime.text = detail.runtime?.runtimeFormat()
            rating.text = detail.vote_average.toString()
            overview.text = detail.overview
            tagline.text = detail.tagline

            val genreAdapter = GenreListAdapter<MovieGenre>()
            detail.genres?.let { genreAdapter.setGenre(it) }
            rvGenre.apply {
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
        bindingTv.detailFilmAttribute.apply {
            Glide.with(this@DetailActivity)
                .load(detail.posterPath?.toImageurl())
                .into(poster)

            title.text = detail.name
            date.text = detail.firstAirDate.dateFormat(this@DetailActivity)
            rating.text = detail.voteAverage.toString()
            overview.text = detail.overview
            tagline.text = detail.tagline

            val genreAdapter = GenreListAdapter<TvGenre>()
            detail.genres?.let { genreAdapter.setGenre(it) }
            rvGenre.apply {
                adapter = genreAdapter
                val flexboxLm = FlexboxLayoutManager(this@DetailActivity)
                flexboxLm.alignItems = AlignItems.STRETCH
                flexboxLm.flexWrap = FlexWrap.WRAP
                layoutManager = flexboxLm
                hasFixedSize()
            }
        }
    }


}