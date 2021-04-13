package com.app.moviecatalogue.presentation.ui.home.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.core.domain.usecase.model.TvShow
import com.app.moviecatalogue.databinding.FilmItemHorizontalBinding
import com.app.moviecatalogue.presentation.utils.dateFormat
import com.app.moviecatalogue.presentation.utils.toImageurl
import com.bumptech.glide.Glide

class FilmItemHorizontalAdapter<T> :
    RecyclerView.Adapter<FilmItemHorizontalAdapter<T>.ItemViewHolder>() {

    private val list = ArrayList<T>()

    fun setData(list: List<T>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmItemHorizontalAdapter<T>.ItemViewHolder =
        ItemViewHolder(
            FilmItemHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: FilmItemHorizontalAdapter<T>.ItemViewHolder,
        position: Int
    ) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size

    inner class ItemViewHolder(private val binding: FilmItemHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(film: T) {
            val posterPath: String?
            val title: String?
            val date: String?
            val rate: Double?

            when (film) {
                is Movie -> {
                    posterPath = film.posterPath
                    title = film.title
                    date = film.releaseDate
                    rate = film.voteAverage
                }
                is TvShow -> {
                    posterPath = film.posterPath
                    title = film.name
                    date = film.firstAirDate
                    rate = film.voteAverage
                }
                else -> {
                    posterPath = "Nothing"
                    title = "Nothing"
                    date = "Nothing"
                    rate = 0.0
                }
            }

            Glide.with(itemView.context)
                .load(posterPath?.toImageurl())
                .into(binding.poster)

            binding.title.text = title.toString()
            binding.date.text = date.dateFormat(itemView.context)
            binding.ratingValue.text = rate.toString()
            binding.ratingbar.apply {
                stepSize = 0.1f
                rating = rate?.toFloat()?.div(2) ?: 0f
            }
        }
    }
}