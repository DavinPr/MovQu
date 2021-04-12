package com.app.moviecatalogue.presentation.ui.home.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.databinding.FilmItemHorizontalBinding
import com.app.moviecatalogue.presentation.utils.toImageurl
import com.bumptech.glide.Glide

class FilmItemHorizontalAdapter :
    RecyclerView.Adapter<FilmItemHorizontalAdapter.ItemViewHolder>() {

    private val list = ArrayList<Movie>()

    fun setData(list: List<Movie>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmItemHorizontalAdapter.ItemViewHolder =
        ItemViewHolder(
            FilmItemHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: FilmItemHorizontalAdapter.ItemViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size

    inner class ItemViewHolder(private val binding: FilmItemHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                .load(movie.posterPath?.toImageurl())
                .into(binding.poster)

            binding.title.text = movie.title.toString()
            binding.date.text = movie.releaseDate.toString()
            binding.ratingValue.text = movie.voteAverage.toString()
        }
    }
}