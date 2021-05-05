package com.app.moviecatalogue.presentation.ui.home.fragment.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.app.moviecatalogue.databinding.FragmentFavoriteBinding
import com.app.moviecatalogue.presentation.ui.detail.DetailActivity
import com.app.moviecatalogue.presentation.ui.home.custom.customdecoration.GridSpacesItemDecoration
import com.app.moviecatalogue.presentation.ui.home.fragment.favorite.category.CategoryListAdapter
import com.app.moviecatalogue.presentation.utils.Constants
import com.google.android.flexbox.AlignItems
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.roundToInt


@ExperimentalCoroutinesApi
@FlowPreview
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoryAdapter = CategoryListAdapter()
        categoryAdapter.setGenre(viewModel.generateCategoryItem())

        val gridAdapter = FavoriteListAdapter()

        lifecycleScope.launch {
            viewModel.queryChannel.send("all")
        }

        binding.rvCategory.apply {
            adapter = categoryAdapter
            val flexboxLm = FlexboxLayoutManager(requireContext())
            flexboxLm.alignItems = AlignItems.STRETCH
            flexboxLm.flexWrap = FlexWrap.WRAP
            layoutManager = flexboxLm
            hasFixedSize()
        }

        categoryAdapter.onClickItem = {
            lifecycleScope.launch {
                viewModel.queryChannel.send(it)
            }
        }

        viewModel.getListResult.observe(viewLifecycleOwner) { result ->
            lifecycleScope.launch {
                result.observe(viewLifecycleOwner) { favorite ->
                    gridAdapter.submitList(favorite)
                }
            }
        }

        gridAdapter.onClick = {
            val type = when (it.type) {
                "movie" -> Constants.MOVIE_TYPE
                "tv" -> Constants.TV_TYPE
                else -> 0
            }
            val intent = Intent(activity, DetailActivity::class.java)
            intent.putExtra(Constants.ID_KEY, it.id.toString())
            intent.putExtra(Constants.TYPE_KEY, type)
            startActivity(intent)
        }

        binding.rvFavorite.apply {
            val spanCount = 3
            val spacing = (4 * resources.displayMetrics.density).roundToInt()

            layoutManager = GridLayoutManager(requireContext(), 3)
            hasFixedSize()
            adapter = gridAdapter
            addItemDecoration(GridSpacesItemDecoration(spanCount, spacing, true))
        }

    }

}