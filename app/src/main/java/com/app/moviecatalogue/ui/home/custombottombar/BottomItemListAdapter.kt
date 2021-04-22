package com.app.moviecatalogue.ui.home.custombottombar

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.app.moviecatalogue.R
import com.app.moviecatalogue.databinding.BottomBarItemBinding

class BottomItemListAdapter(
    var selected: Int,
    val bottomItems: ArrayList<BottomItem>,
    val itemWidth: Int
) : RecyclerView.Adapter<BottomItemListAdapter.BottomViewHolder>() {

    var onClick: ((BottomItem) -> Unit)? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BottomItemListAdapter.BottomViewHolder =
        BottomViewHolder(
            BottomBarItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: BottomItemListAdapter.BottomViewHolder, position: Int) {
        val item = bottomItems[position]
        holder.apply {
            resizeItemWidth()
            setItem(item)
            selectedStyle(item)
        }
    }

    override fun getItemCount(): Int = bottomItems.size

    inner class BottomViewHolder(private val binding: BottomBarItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun setItem(bottomItem: BottomItem) {
            binding.bottomIcon.setImageResource(bottomItem.itemDefaultIcon)
            binding.bottomTitle.text = bottomItem.itemTitle
        }

        fun selectedStyle(item: BottomItem) {
            if (item.itemId == selected) {
                binding.bottomIcon.apply {
                    imageTintList = null
                    setImageResource(item.itemSelectedIcon)
                }
                binding.bottomTitle.setTextColor(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        R.color.primaryColor
                    )
                )
            } else {
                binding.bottomIcon.apply {
                    setImageResource(item.itemDefaultIcon)
                    imageTintList =
                        ContextCompat.getColorStateList(
                            itemView.context,
                            android.R.color.darker_gray
                        )
                }

                binding.bottomTitle.setTextColor(
                    ContextCompat.getColorStateList(
                        itemView.context,
                        android.R.color.darker_gray
                    )
                )
            }
        }

        fun resizeItemWidth() {
            binding.bottomIconParent.layoutParams.width = itemWidth
        }

        init {
            binding.bottomIconParent.setOnClickListener {
                Handler(Looper.getMainLooper()).postDelayed({
                    onClick?.invoke(bottomItems[adapterPosition])
                    selected = bottomItems[adapterPosition].itemId
                    selectedStyle(bottomItems[adapterPosition])
                    notifyDataSetChanged()
                }, 200)
            }
        }
    }
}