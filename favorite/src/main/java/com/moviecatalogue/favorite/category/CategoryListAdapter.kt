package com.moviecatalogue.favorite.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.moviecatalogue.R
import com.moviecatalogue.favorite.databinding.HomeCategoryItemBinding

class CategoryListAdapter : RecyclerView.Adapter<CategoryListAdapter.GenreViewHolder>() {

    private val list = ArrayList<Category>()
    private var currPos: Int = 0
    var onClickItem: ((String) -> Unit)? = null

    fun setGenre(list: List<Category>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GenreViewHolder =
        GenreViewHolder(
            HomeCategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        val category = list[position]
        if (position == currPos) {
            holder.setBackgroundColor(R.color.primaryColor, true)
        } else {
            holder.setBackgroundColor(R.color.light_gray, false)
        }
        holder.bind(category)
    }

    override fun getItemCount(): Int = list.size

    inner class GenreViewHolder(private val binding: HomeCategoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(category: Category) {
            binding.categoryName.text = category.name
        }

        fun setBackgroundColor(color: Int, active: Boolean) {
            binding.categoryName.backgroundTintList =
                ContextCompat.getColorStateList(
                    itemView.context,
                    color
                )
            if (active) {
                binding.categoryName.setTextColor(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.white
                    )
                )
            } else {
                binding.categoryName.setTextColor(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.black
                    )
                )
            }
        }

        init {
            itemView.setOnClickListener {
                currPos = adapterPosition
                onClickItem?.invoke(list[adapterPosition].key)
                notifyDataSetChanged()
            }
        }
    }
}