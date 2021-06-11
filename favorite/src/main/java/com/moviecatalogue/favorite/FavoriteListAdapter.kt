package com.moviecatalogue.favorite

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.moviecatalogue.databinding.FilmItemGridBinding
import com.app.moviecatalogue.presentation.utils.toImageurl
import com.bumptech.glide.Glide
import com.moviecatalogue.core.domain.usecase.model.Favorite

class FavoriteListAdapter : PagedListAdapter<Favorite, FavoriteListAdapter.GridViewHolder>(
    DIFF_CALLBACK
) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Favorite>() {
            override fun areItemsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Favorite, newItem: Favorite): Boolean {
                return oldItem == newItem
            }
        }
    }

    var onClick: ((Favorite) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridViewHolder =
        GridViewHolder(
            FilmItemGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
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

        fun bind(item: Favorite) {
            binding.gridTitle.text = item.title
            Glide.with(itemView.context)
                .load(item.posterPath?.toImageurl())
                .into(binding.gridPoster)
        }
    }
}