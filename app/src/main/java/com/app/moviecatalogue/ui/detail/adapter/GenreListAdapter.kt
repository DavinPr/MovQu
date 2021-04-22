package com.app.moviecatalogue.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.moviecatalogue.databinding.DetailCategoryItemBinding

class GenreListAdapter : RecyclerView.Adapter<GenreListAdapter.GenreViewHolder>() {

    private val list = ArrayList<String>()

    fun setGenre(list: List<String>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenreListAdapter.GenreViewHolder =
        GenreViewHolder(
            DetailCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: GenreListAdapter.GenreViewHolder, position: Int) {
        val genre = list[position]
        holder.bind(genre)
    }

    override fun getItemCount(): Int = list.size

    inner class GenreViewHolder(private val binding: DetailCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(genre: String) {
            binding.genreName.text = genre
        }
    }
}