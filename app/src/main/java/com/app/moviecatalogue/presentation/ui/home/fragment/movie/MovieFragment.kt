package com.app.moviecatalogue.presentation.ui.home.fragment.movie

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
import com.app.moviecatalogue.databinding.FragmentMovieBinding
import com.app.moviecatalogue.presentation.ui.home.fragment.adapter.FilmItemCarouselAdapter
import com.app.moviecatalogue.presentation.ui.home.fragment.adapter.FilmItemHorizontalAdapter
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

        val discoverAdapter = FilmItemCarouselAdapter()
        viewModel.getDiscover.observe(viewLifecycleOwner) { movies ->
            val data = movies.data
            if (data != null) {
                binding.discoverLayout.apply {
                    btnNext.visibility = View.VISIBLE
                    btnPrev.visibility = View.VISIBLE
                    indicator.visibility = View.VISIBLE
                }
                discoverAdapter.setData(movies.data)
                binding.discoverLayout.discoverTitle.text = data[0].title
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
                    binding.discoverLayout.discoverTitle.text = if (data.size != 0) {
                        data[position].title
                    } else {
                        "Not Found"
                    }
                }
            })


        }

        val nowPlayingAdapter = FilmItemHorizontalAdapter()
        viewModel.getNowPlaying.observe(viewLifecycleOwner) { movies ->
            val data = movies.data
            if (data != null) {
                nowPlayingAdapter.setData(movies.data)
            }
        }


        binding.nowPlayingLayout.apply {
            tvTitleCategory.text = resources.getString(R.string.now_playing)
            rvFilm.also {
                it.adapter = nowPlayingAdapter
                it.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                it.hasFixedSize()
            }
        }


        val upcomingAdapter = FilmItemHorizontalAdapter()
        viewModel.getUpcoming.observe(viewLifecycleOwner) { movies ->
            val data = movies.data
            if (data != null) {
                upcomingAdapter.setData(movies.data)
            }
        }

        binding.upcomingLayout.apply {
            tvTitleCategory.text = resources.getString(R.string.upcoming)
            rvFilm.also {
                it.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                it.hasFixedSize()
                it.adapter = upcomingAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}