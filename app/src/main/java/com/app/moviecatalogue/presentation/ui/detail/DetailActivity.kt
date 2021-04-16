package com.app.moviecatalogue.presentation.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.moviecatalogue.R
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.domain.usecase.model.MovieDetail
import com.app.moviecatalogue.core.domain.usecase.model.MovieGenre
import com.app.moviecatalogue.core.domain.usecase.model.TvDetail
import com.app.moviecatalogue.core.domain.usecase.model.TvGenre
import com.app.moviecatalogue.databinding.ActivityDetailBinding
import com.app.moviecatalogue.presentation.ui.detail.adapter.GenreListAdapter
import com.app.moviecatalogue.presentation.utils.dateFormat
import com.app.moviecatalogue.presentation.utils.runtimeFormat
import com.app.moviecatalogue.presentation.utils.toImageurl
import com.app.moviecatalogue.presentation.utils.toStringList
import com.bumptech.glide.Glide
import com.google.android.flexbox.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding
    private val viewModel: DetailViewModel by viewModel()

    companion object {
        const val ID_KEY = "id_key"
        const val TYPE_KEY = "type_key"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getIntExtra(TYPE_KEY, 101)
        val id = intent.getStringExtra(ID_KEY)
        when (type) {
            101 -> {
                binding.detailMovieAttribute.apply {
                    root.visibility = View.VISIBLE
                }
                if (id != null) setDetailMovieView(id)
            }
            102 -> {
                binding.detailTvAttribute.apply {
                    root.visibility = View.VISIBLE
                }
                if (id != null) setDetailTvView(id)
            }
        }
        binding.toolbar.apply {
            btnBack.setOnClickListener(this@DetailActivity)
            btnFavorite.setOnClickListener(this@DetailActivity)
        }
    }

    private fun setDetailMovieView(id: String) {
        binding.toolbar.activityTitle.text = resources.getString(R.string.movie)
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
        binding.toolbar.activityTitle.text = resources.getString(R.string.tvshow)
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
        binding.detailMovieAttribute.apply {
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
        binding.detailTvAttribute.apply {
            Glide.with(this@DetailActivity)
                .load(detail.posterPath?.toImageurl())
                .into(poster)

            title.text = detail.name
            firstDate.text = detail.firstAirDate?.dateFormat(this@DetailActivity) ?: "-"
            lastDate.text = detail.lastAirDate?.dateFormat(this@DetailActivity) ?: "-"
            rating.text = detail.voteAverage.toString()
            overview.text = detail.overview
            episodes.text = detail.numberOfEpisodes?.toString() ?: "-"
            seasons.text = detail.numberOfSeasons?.toString() ?: "-"

            detail.episodeRunTime?.let {
                if (it.isNotEmpty()) runtime.text = it.toStringList()
            }
            tagline.text = detail.tagline ?: resources.getString(R.string.no_tagline)

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

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.toolbar.btnBack.id -> finish()
            binding.toolbar.btnFavorite.id -> {
                Toast.makeText(this, "Implemented soon", Toast.LENGTH_SHORT).show()
            }
        }
    }


}