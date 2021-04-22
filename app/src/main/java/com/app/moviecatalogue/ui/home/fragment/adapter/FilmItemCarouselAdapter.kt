package com.app.moviecatalogue.ui.home.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.moviecatalogue.data.Movie
import com.app.moviecatalogue.data.TvShow
import com.app.moviecatalogue.databinding.FilmItemCarouselBinding
import com.app.moviecatalogue.utils.toImageurl
import com.bumptech.glide.Glide

class FilmItemCarouselAdapter<T> :
    RecyclerView.Adapter<FilmItemCarouselAdapter<T>.ItemViewHolder>() {

    private val list = ArrayList<T>()
    var onClick: ((T) -> Unit)? = null

    fun setData(list: List<T>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    fun getData() = list

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmItemCarouselAdapter<T>.ItemViewHolder =
        ItemViewHolder(
            FilmItemCarouselBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(
        holder: FilmItemCarouselAdapter<T>.ItemViewHolder,
        position: Int
    ) {
        val data = list[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = list.size

    inner class ItemViewHolder(private val binding: FilmItemCarouselBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                onClick?.invoke(list[adapterPosition])
            }
        }

        fun bind(film: T) {
            val imagePath = when (film) {
                is Movie -> film.backdropPath
                is TvShow -> film.backdropPath
                else -> "Nothing"
            }

            Glide.with(itemView.context)
                .load(imagePath?.toImageurl())
                .into(binding.backdrop)
        }
    }
}