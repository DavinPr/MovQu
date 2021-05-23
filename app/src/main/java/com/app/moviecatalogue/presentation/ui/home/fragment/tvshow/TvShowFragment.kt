package com.app.moviecatalogue.presentation.ui.home.fragment.tvshow

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
import com.moviecatalogue.core.utils.Constants.ID_KEY
import com.moviecatalogue.core.utils.Constants.LIST_TYPE
import com.moviecatalogue.core.utils.Constants.TV_AIRING_TODAY_TYPE
import com.moviecatalogue.core.utils.Constants.TV_DISCOVER_TYPE
import com.moviecatalogue.core.utils.Constants.TV_ON_THE_AIR_TYPE
import com.moviecatalogue.core.utils.Constants.TV_TYPE
import com.moviecatalogue.core.utils.Constants.TYPE_KEY
import com.app.moviecatalogue.R
import com.moviecatalogue.core.data.Resource
import com.moviecatalogue.core.domain.usecase.model.TvShow
import com.app.moviecatalogue.databinding.FragmentTvShowBinding
import com.app.moviecatalogue.presentation.ui.allfilm.AllFilmActivity
import com.app.moviecatalogue.presentation.ui.detail.DetailActivity
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

        binding.searchField.setOnClickListener { implementedSoon() }

        val discoverAdapter = FilmItemCarouselAdapter<TvShow>()
        viewModel.getTvDiscover.observe(viewLifecycleOwner) { tv ->
            val data = tv.data
            binding.discoverLayout.discoverLoading.root.apply {
                isVisible = tv is Resource.Loading && data.isNullOrEmpty()
            }
            if (data != null && data.isNotEmpty()) {
                binding.discoverLayout.apply {
                    indicator.visibility = View.VISIBLE
                    if (data.size > 1) {
                        btnNext.visibility = View.VISIBLE
                    }
                }
                discoverAdapter.setData(data)
                binding.discoverLayout.apply {
                    discoverTitle.text = data[0].name
                    discoverRating.also {
                        it.stepSize = 0.1f
                        it.rating = data[0].voteAverage?.toFloat()?.div(2) ?: 0f
                    }
                }
            }
        }
        binding.discoverLayout.viewAll.setOnClickListener {
            val intent = Intent(activity, AllFilmActivity::class.java)
            intent.putExtra(TYPE_KEY, TV_TYPE)
            intent.putExtra(LIST_TYPE, TV_DISCOVER_TYPE)
            startActivity(intent)
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
                                    btnNext.visibility = View.VISIBLE
                                    btnPrev.visibility = View.GONE
                                }
                                data.size - 1 -> {
                                    btnNext.visibility = View.GONE
                                    btnPrev.visibility = View.VISIBLE
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

        discoverAdapter.onClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(ID_KEY, it.id.toString())
            intent.putExtra(TYPE_KEY, TV_TYPE)
            startActivity(intent)
        }

        val airingTodayAdapter = FilmItemHorizontalAdapter<TvShow>()
        viewModel.getTvAiringToday.observe(viewLifecycleOwner) { tv ->
            val data = tv.data
            binding.airingTodayLayout.listFilmLoading.root.apply {
                isVisible = tv is Resource.Loading && data.isNullOrEmpty()
            }
            if (data != null) {
                airingTodayAdapter.setData(data)
            }
        }


        binding.airingTodayLayout.apply {
            viewAll.setOnClickListener {
                val intent = Intent(activity, AllFilmActivity::class.java)
                intent.putExtra(TYPE_KEY, TV_TYPE)
                intent.putExtra(LIST_TYPE, TV_AIRING_TODAY_TYPE)
                startActivity(intent)
            }
            tvTitleCategory.text = resources.getString(R.string.airing_today)
            rvFilm.also {
                it.adapter = airingTodayAdapter
                it.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                it.hasFixedSize()
            }
        }

        airingTodayAdapter.onClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(ID_KEY, it.id.toString())
            intent.putExtra(TYPE_KEY, TV_TYPE)
            startActivity(intent)
        }


        val onTheAirAdapter = FilmItemHorizontalAdapter<TvShow>()
        viewModel.getTvOnTheAir.observe(viewLifecycleOwner) { tv ->
            val data = tv.data
            binding.onTheAirLayout.listFilmLoading.root.apply {
                isVisible = tv is Resource.Loading && data.isNullOrEmpty()
            }
            if (data != null) {
                onTheAirAdapter.setData(data)
            }
        }

        binding.onTheAirLayout.apply {
            viewAll.setOnClickListener {
                val intent = Intent(activity, AllFilmActivity::class.java)
                intent.putExtra(TYPE_KEY, TV_TYPE)
                intent.putExtra(LIST_TYPE, TV_ON_THE_AIR_TYPE)
                startActivity(intent)
            }
            tvTitleCategory.text = resources.getString(R.string.on_the_air)
            rvFilm.also {
                it.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                it.hasFixedSize()
                it.adapter = onTheAirAdapter
            }
        }

        onTheAirAdapter.onClick = {
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(ID_KEY, it.id.toString())
            intent.putExtra(TYPE_KEY, TV_TYPE)
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