package com.app.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.app.moviecatalogue.R
import com.app.moviecatalogue.data.Movie
import com.app.moviecatalogue.data.TvShow
import com.app.moviecatalogue.databinding.ActivityDetailBinding
import com.app.moviecatalogue.ui.detail.adapter.GenreListAdapter
import com.app.moviecatalogue.utils.*
import com.bumptech.glide.Glide
import com.google.android.flexbox.*

class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityDetailBinding

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
        val listMovie = DataDummy.generateDummyMovie()
        val movie = listMovie.first { movie ->
            movie.id.toString() == id
        }
        setDataMovie(movie)
    }

    private fun setDetailTvView(id: String) {
        binding.toolbar.activityTitle.text = resources.getString(R.string.tvshow)
        val listTv = DataDummy.generateDummyTv()
        val tv = listTv.first { tv ->
            tv.id.toString() == id
        }
        setDataTv(tv)
    }

    private fun setDataMovie(detail: Movie) {
        binding.detailMovieAttribute.apply {
            Glide.with(this@DetailActivity)
                .load(detail.posterPath?.toImageurl())
                .into(posterMovie)

            titleMovie.text = detail.title
            dateMovie.text = detail.releaseDate.dateFormat(this@DetailActivity)
            runtimeMovie.text = detail.runtime?.runtimeFormat()
            ratingMovie.text = detail.voteAverage.toString()
            overviewMovie.text = detail.overview
            taglineMovie.text = detail.tagline

            val genreAdapter = GenreListAdapter()
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

    private fun setDataTv(detail: TvShow) {
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

            val genreAdapter = GenreListAdapter()
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

    override fun onClick(v: View?) {
        when (v?.id) {
            binding.toolbar.btnBack.id -> finish()
            binding.toolbar.btnFavorite.id -> {
                Toast.makeText(this, "Implemented soon", Toast.LENGTH_SHORT).show()
            }
        }
    }


}