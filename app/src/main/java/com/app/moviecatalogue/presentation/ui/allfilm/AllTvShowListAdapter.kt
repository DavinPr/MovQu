package com.app.moviecatalogue.presentation.ui.allfilm

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.moviecatalogue.R
import com.moviecatalogue.core.domain.usecase.model.TvShow
import com.app.moviecatalogue.databinding.FilmItemGridBinding
import com.app.moviecatalogue.presentation.utils.toImageurl
import com.bumptech.glide.Glide

class AllTvShowListAdapter :
    PagedListAdapter<TvShow, AllTvShowListAdapter.GridViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShow>() {
            override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
                return oldItem == newItem
            }
        }
    }

    var onClick: ((TvShow) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AllTvShowListAdapter.GridViewHolder =
        GridViewHolder(
            FilmItemGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AllTvShowListAdapter.GridViewHolder, position: Int) {
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

        fun bind(item: TvShow) {
            binding.gridTitle.text = item.name
            Glide.with(itemView.context)
                .load(item.posterPath?.toImageurl())
                .placeholder(R.drawable.ic_image)
                .into(binding.gridPoster)
        }
    }
}