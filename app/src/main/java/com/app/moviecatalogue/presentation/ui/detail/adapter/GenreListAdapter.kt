package com.app.moviecatalogue.presentation.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moviecatalogue.core.domain.usecase.model.MovieGenre
import com.moviecatalogue.core.domain.usecase.model.TvGenre
import com.app.moviecatalogue.databinding.DetailCategoryItemBinding

class GenreListAdapter<T> : RecyclerView.Adapter<GenreListAdapter<T>.GenreViewHolder>() {

    private val list = ArrayList<T>()

    fun setGenre(list: List<T>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenreListAdapter<T>.GenreViewHolder =
        GenreViewHolder(
            DetailCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: GenreListAdapter<T>.GenreViewHolder, position: Int) {
        val genre = list[position]
        holder.bind(genre)
    }

    override fun getItemCount(): Int = list.size

    inner class GenreViewHolder(private val binding: DetailCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: T) {
            when (genre) {
                is MovieGenre -> binding.genreName.text = genre.name
                is TvGenre -> binding.genreName.text = genre.name
            }
        }
    }
}