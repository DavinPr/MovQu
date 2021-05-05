package com.app.moviecatalogue.presentation.ui.home.fragment.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.app.moviecatalogue.R
import com.app.moviecatalogue.core.data.Resource
import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.databinding.FragmentMovieBinding
import com.app.moviecatalogue.presentation.ui.detail.DetailActivity
import com.app.moviecatalogue.presentation.ui.home.fragment.adapter.FilmItemCarouselAdapter
import com.app.moviecatalogue.presentation.ui.home.fragment.adapter.FilmItemHorizontalAdapter
import com.app.moviecatalogue.presentation.utils.Constants.ID_KEY
import com.app.moviecatalogue.presentation.utils.Constants.MOVIE_TYPE
import com.app.moviecatalogue.presentation.utils.Constants.TYPE_KEY
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.searchField.setOnClickListener { implementedSoon() }

        val discoverAdapter = FilmItemCarouselAdapter<Movie>()
        viewModel.getDiscover.observe(viewLifecycleOwner) { movies ->
            val data = movies.data
            binding.discoverLayout.discoverLoading.root.isVisible =
                movies is Resource.Loading && data.isNullOrEmpty()
            if (data != null && data.isNotEmpty()) {
                binding.discoverLayout.apply {
                    indicator.visibility = View.VISIBLE
                    if (data.size > 1) {
                        btnNext.visibility = View.VISIBLE
                    }
                }
                discoverAdapter.setData(movies.data)
                binding.discoverLayout.apply {
                    discoverTitle.text = data[0].title
                    discoverRating.also {
                        it.stepSize = 0.1f
                        it.rating = data[0].voteAverage?.toFloat()?.div(2) ?: 0f
                    }
                }
            }
        }

        binding.discoverLayout.viewAll.setOnClickListener {
            implementedSoon()
        }
        binding.discoverLayout.viewpagerFilm.apply {
            adapter = discoverAdapter
            clipToPadding = false
            clipChildren = false
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

            val compositePageTransformer = CompositePageTransformer()
            compositePageTransformer.addTransformer(MarginPageTransformer(40))
            setPageTransformer(compositePageTransformer)
            binding.discoverLayout.indicator.setViewPager2(this)

            binding.discoverLayout.btnNext.setOnClickListener {
                setCurrentItem(currentItem + 1, true)
            }

            binding.discoverLayout.btnPrev.setOnClickListener {
                setCurrentItem(currentItem - 1, true)
            }

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val data = discoverAdapter.getData()
                    if (data.size != 0) {
                        binding.discoverLayout.apply {
                            when (position) {
                                0 -> {
                                    btnPrev.visibility = View.GONE
                                    btnNext.visibility = View.VISIBLE
                                }
                                data.size - 1 -> {
                                    btnPrev.visibility = View.VISIBLE
                                    btnNext.visibility = View.GONE
                                }
                                else -> {
                                    btnNext.visibility = View.VISIBLE
                                    btnPrev.visibility = View.VISIBLE
                                }
                            }
                            data[position].also {
                                discoverRating.apply {
                                    stepSize = 0.1f
                                    rating = it.voteAverage?.toFloat()?.div(2) ?: 0f
                                }
                                discoverTitle.text = it.title
                            }
                        }
                    } else {
                        binding.discoverLayout.discoverTitle.text =
                            this@MovieFragment.resources.getString(R.string.no_data)
                    }
                }
            })
        }

        discoverAdapter.onClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(ID_KEY, it.id.toString())
            intent.putExtra(TYPE_KEY, MOVIE_TYPE)
            startActivity(intent)
        }

        val nowPlayingAdapter = FilmItemHorizontalAdapter<Movie>()
        viewModel.getNowPlaying.observe(viewLifecycleOwner) { movies ->
            val data = movies.data
            binding.nowPlayingLayout.listFilmLoading.root.apply {
                isVisible = movies is Resource.Loading && data.isNullOrEmpty()
            }
            if (data != null) {
                nowPlayingAdapter.setData(movies.data)
            }
        }

        binding.nowPlayingLayout.apply {
            viewAll.setOnClickListener { implementedSoon() }
            tvTitleCategory.text = resources.getString(R.string.now_playing)
            rvFilm.also {
                it.adapter = nowPlayingAdapter
                it.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                it.hasFixedSize()
            }
        }

        nowPlayingAdapter.onClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(ID_KEY, it.id.toString())
            intent.putExtra(TYPE_KEY, MOVIE_TYPE)
            startActivity(intent)
        }


        val upcomingAdapter = FilmItemHorizontalAdapter<Movie>()
        viewModel.getUpcoming.observe(viewLifecycleOwner) { movies ->
            val data = movies.data
            binding.upcomingLayout.listFilmLoading.root.apply {
                isVisible = movies is Resource.Loading && data.isNullOrEmpty()
            }
            if (data != null) {
                upcomingAdapter.setData(movies.data)
            }
        }

        binding.upcomingLayout.apply {
            viewAll.setOnClickListener { implementedSoon() }
            tvTitleCategory.text = resources.getString(R.string.upcoming)
            rvFilm.also {
                it.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                it.hasFixedSize()
                it.adapter = upcomingAdapter
            }
        }

        upcomingAdapter.onClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(ID_KEY, it.id.toString())
            intent.putExtra(TYPE_KEY, MOVIE_TYPE)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun implementedSoon() {
        Toast.makeText(requireContext(), "Implemented Soon", Toast.LENGTH_SHORT).show()
    }
}