package com.app.moviecatalogue.presentation.ui.home.fragment.tvshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.app.moviecatalogue.R
import com.app.moviecatalogue.core.domain.usecase.model.TvShow
import com.app.moviecatalogue.databinding.FragmentTvShowBinding
import com.app.moviecatalogue.presentation.ui.home.fragment.adapter.FilmItemCarouselAdapter
import com.app.moviecatalogue.presentation.ui.home.fragment.adapter.FilmItemHorizontalAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment() {

    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<TvShowViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val discoverAdapter = FilmItemCarouselAdapter<TvShow>()
        viewModel.getTvDiscover.observe(viewLifecycleOwner) { tv ->
            val data = tv.data
            if (data != null && data.isNotEmpty()) {
                binding.discoverLayout.apply {
                    indicator.visibility = View.VISIBLE
                    if (data.size > 1) {
                        btnNext.visibility = View.VISIBLE
                    }
                }
                discoverAdapter.setData(tv.data)
                binding.discoverLayout.apply {
                    discoverTitle.text = data[0].name
                    discoverRating.also {
                        it.stepSize = 0.1f
                        it.rating = data[0].voteAverage?.toFloat()?.div(2) ?: 0f
                    }
                }
            }
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

            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    val data = discoverAdapter.getData()
                    if (data.size != 0) {
                        binding.discoverLayout.apply {
                            when (position) {
                                0 -> btnPrev.visibility = View.GONE
                                data.size - 1 -> btnNext.visibility = View.GONE
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
                                discoverTitle.text = it.name
                            }
                        }
                    } else {
                        binding.discoverLayout.discoverTitle.text =
                            this@TvShowFragment.resources.getString(R.string.no_data)
                    }
                }
            })


        }

        val airingTodayAdapter = FilmItemHorizontalAdapter<TvShow>()
        viewModel.getTvAiringToday.observe(viewLifecycleOwner) { tv ->
            val data = tv.data
            if (data != null) {
                airingTodayAdapter.setData(tv.data)
            }
        }


        binding.airingTodayLayout.apply {
            tvTitleCategory.text = resources.getString(R.string.airing_today)
            rvFilm.also {
                it.adapter = airingTodayAdapter
                it.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                it.hasFixedSize()
            }
        }


        val onTheAirAdapter = FilmItemHorizontalAdapter<TvShow>()
        viewModel.getTvOnTheAir.observe(viewLifecycleOwner) { tv ->
            val data = tv.data
            if (data != null) {
                onTheAirAdapter.setData(tv.data)
            }
        }

        binding.onTheAirLayout.apply {
            tvTitleCategory.text = resources.getString(R.string.on_the_air)
            rvFilm.also {
                it.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                it.hasFixedSize()
                it.adapter = onTheAirAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}