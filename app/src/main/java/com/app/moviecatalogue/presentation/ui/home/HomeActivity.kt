package com.app.moviecatalogue.presentation.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.moviecatalogue.R
import com.app.moviecatalogue.databinding.ActivityHomeBinding
import com.app.moviecatalogue.presentation.ui.home.custombottombar.BottomItem
import com.app.moviecatalogue.presentation.ui.home.custombottombar.BottomItemListAdapter
import com.app.moviecatalogue.presentation.ui.home.fragment.favorite.FavoriteFragment
import com.app.moviecatalogue.presentation.ui.home.fragment.movie.MovieFragment
import com.app.moviecatalogue.presentation.ui.search.SearchFragment
import com.app.moviecatalogue.presentation.ui.home.fragment.tvshow.TvShowFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val iconList = ArrayList<BottomItem>()
    private lateinit var bottomAdapter: BottomItemListAdapter
    private var itemId: Int = 0
    private lateinit var toolbarSubTitle: String

    companion object {
        private const val currentId = "current_id"
        private const val currentTitle = "current_title"
        private const val iconLimit = 4
        private const val MOVIE = 0
        private const val TV = 1
        private const val SEARCH = 2
        private const val FAVORITE = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar.root)

        initBottomItems()
        if (savedInstanceState == null) {
            toolbarSubTitle = resources.getString(R.string.movie)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.add(binding.fragmentContainer.id, MovieFragment())
            transaction.commit()

            bottomAdapter =
                BottomItemListAdapter(
                    MOVIE,
                    iconList,
                    calculateItemWidth()
                )
        } else {
            toolbarSubTitle =
                savedInstanceState.getString(currentTitle, resources.getString(R.string.movie))
            bottomAdapter =
                BottomItemListAdapter(
                    savedInstanceState.getInt(currentId, 0),
                    iconList,
                    calculateItemWidth()
                )
        }
        binding.toolbar.fragmentTitle.text = toolbarSubTitle
        setAdapter()

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(currentId, itemId)
        outState.putString(currentTitle, toolbarSubTitle)
    }

    private fun initBottomItems() {
        val movie = BottomItem(
            MOVIE,
            resources.getString(R.string.movie),
            R.drawable.ic_movie_border,
            R.drawable.ic_movie_filled
        )
        val tv = BottomItem(
            TV,
            resources.getString(R.string.tvshow),
            R.drawable.ic_tv_border,
            R.drawable.ic_tv_filled
        )
        val favorite = BottomItem(
            FAVORITE,
            resources.getString(R.string.favorite),
            R.drawable.ic_love_border,
            R.drawable.ic_love_filled
        )
        addBottomIcons(movie)
        addBottomIcons(tv)
        addBottomIcons(favorite)
    }

    private fun setAdapter() {
        binding.homeBottomBar.rvBottomIcon.apply {
            layoutManager = LinearLayoutManager(
                this@HomeActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            adapter = bottomAdapter
        }

        bottomAdapter.onClick = { item ->
            var selectedFragment: Fragment? = null

            item.itemTitle.let {
                this.toolbarSubTitle = it
                binding.toolbar.fragmentTitle.text = it
            }

            item.itemId.let {
                this.itemId = it
                when (it) {
                    MOVIE -> selectedFragment = MovieFragment()
                    TV -> selectedFragment = TvShowFragment()
                    SEARCH -> selectedFragment = SearchFragment()
                    FAVORITE -> selectedFragment = FavoriteFragment()
                }
            }

            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(binding.fragmentContainer.id, selectedFragment as Fragment)
            transaction.commit()
        }
    }

    private fun calculateItemWidth(): Int {
        val mCount: Int = iconList.size
        val mWidth: Int = resources.displayMetrics.widthPixels
        return mWidth / mCount
    }

    private fun addBottomIcons(bottomItem: BottomItem) {
        if (iconList.size != iconLimit) {
            iconList.add(bottomItem)
        }
    }
}

