package com.app.moviecatalogue.presentation.ui.home.fragment.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.moviecatalogue.R
import com.app.moviecatalogue.databinding.FragmentMovieBinding
import com.app.moviecatalogue.presentation.ui.home.fragment.adapter.ListFilmCarouselAdapter
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

        val discoverAdapter = ListFilmCarouselAdapter()
        viewModel.getDiscover.observe(viewLifecycleOwner) { movies ->
            val data = movies.data
            if (data != null) {
                discoverAdapter.setData(movies.data)
            }
        }

        binding.discoverLayout.rvFilm.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            hasFixedSize()
            adapter = discoverAdapter
        }

        val nowPlayingAdapter = ListFilmCarouselAdapter()
        viewModel.getNowPlaying.observe(viewLifecycleOwner) { movies ->
            val data = movies.data
            if (data != null) {
                nowPlayingAdapter.setData(movies.data)
            }
        }


        binding.nowPlayingLayout.apply {
            tvTitleCategory.text = resources.getString(R.string.now_playing)
            rvFilm.also {
                it.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                it.hasFixedSize()
                it.adapter = nowPlayingAdapter
            }
        }


        val upcomingAdapter = ListFilmCarouselAdapter()
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