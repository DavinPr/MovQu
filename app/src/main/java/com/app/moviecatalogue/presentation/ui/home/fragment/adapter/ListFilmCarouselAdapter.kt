package com.app.moviecatalogue.presentation.ui.home.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.moviecatalogue.core.domain.usecase.model.Movie
import com.app.moviecatalogue.databinding.FilmCarouselBinding
import com.app.moviecatalogue.presentation.utils.toImageurl
import com.bumptech.glide.Glide

class ListFilmCarouselAdapter : RecyclerView.Adapter<ListFilmCarouselAdapter.ListViewHolder>() {

    private val list = ArrayList<Movie>()

    fun setData(list: List<Movie>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ListFilmCarouselAdapter.ListViewHolder =
        ListViewHolder(
            FilmCarouselBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListFilmCarouselAdapter.ListViewHolder, position: Int) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size

    inner class ListViewHolder(private val binding: FilmCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            Glide.with(itemView.context)
                .load(movie.backdropPath?.toImageurl())
                .into(binding.backdrop)

            binding.title.text = movie.title
        }
    }
}