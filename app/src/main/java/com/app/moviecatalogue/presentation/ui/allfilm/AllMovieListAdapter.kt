package com.app.moviecatalogue.presentation.ui.allfilm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.moviecatalogue.R
import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.databinding.FilmItemGridBinding
import com.app.moviecatalogue.presentation.utils.toImageurl
import com.bumptech.glide.Glide

class AllMovieListAdapter :
    PagedListAdapter<Movie, AllMovieListAdapter.GridViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }
    }

    var onClick: ((Movie) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllMovieListAdapter.GridViewHolder =
        GridViewHolder(
            FilmItemGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AllMovieListAdapter.GridViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class GridViewHolder(private val binding: FilmItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                currentList?.get(adapterPosition)?.let { item ->
                    onClick?.invoke(item)
                }
            }
        }

        fun bind(item: Movie) {
            binding.gridTitle.text = item.title
            Glide.with(itemView.context)
                .load(item.posterPath?.toImageurl())
                .placeholder(R.drawable.ic_image)
                .into(binding.gridPoster)
        }
    }
}