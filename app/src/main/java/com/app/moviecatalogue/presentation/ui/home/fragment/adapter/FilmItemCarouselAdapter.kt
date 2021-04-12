package com.app.moviecatalogue.presentation.ui.home.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.databinding.FilmItemCarouselBinding
import com.app.moviecatalogue.presentation.utils.toImageurl
import com.bumptech.glide.Glide

class FilmItemCarouselAdapter : RecyclerView.Adapter<FilmItemCarouselAdapter.ItemViewHolder>() {

    private val list = ArrayList<Movie>()

    fun setData(list: List<Movie>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun getData() = list

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmItemCarouselAdapter.ItemViewHolder =
        ItemViewHolder(
            FilmItemCarouselBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FilmItemCarouselAdapter.ItemViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size

    inner class ItemViewHolder(private val binding: FilmItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                .load(movie.backdropPath?.toImageurl())
                .into(binding.backdrop)
        }
    }
}