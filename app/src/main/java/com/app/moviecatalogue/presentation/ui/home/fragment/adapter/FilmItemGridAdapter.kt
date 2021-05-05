package com.app.moviecatalogue.presentation.ui.home.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.moviecatalogue.core.domain.usecase.model.Favorite
import com.app.moviecatalogue.databinding.FilmItemGridBinding
import com.app.moviecatalogue.presentation.utils.toImageurl
import com.bumptech.glide.Glide

class FilmItemGridAdapter<T> : RecyclerView.Adapter<FilmItemGridAdapter<T>.GridViewHolder>() {

    private val list = ArrayList<T>()

    fun setData(list: List<T>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmItemGridAdapter<T>.GridViewHolder =
        GridViewHolder(
            FilmItemGridBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FilmItemGridAdapter<T>.GridViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size

    inner class GridViewHolder(private val binding: FilmItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: T) {
            var imageUrl: String? = null
            when (item) {
                is Favorite -> {
                    imageUrl = item.posterPath?.toImageurl()
                }
            }
            Glide.with(itemView.context)
                .load(imageUrl)
                .into(binding.gridPoster)
        }
    }
}