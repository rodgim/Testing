package com.rodgim.testing.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.rodgim.testing.data.local.ShoppingItem
import com.rodgim.testing.databinding.ItemShoppingBinding
import javax.inject.Inject

class ShoppingItemAdapter @Inject constructor(
    private val glide: RequestManager
) : ListAdapter<ShoppingItem, ShoppingItemAdapter.ShoppingItemViewHolder>(ShoppingItemDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        return ShoppingItemViewHolder(
            ItemShoppingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item, glide)
    }

    class ShoppingItemViewHolder(private val view: ItemShoppingBinding): RecyclerView.ViewHolder(view.root) {
        fun bind(item: ShoppingItem, glide: RequestManager) {
            view.apply {
                glide.load(item.imageUrl).into(ivShoppingImage)
                tvName.text = item.name
                tvShoppingItemAmount.text = "${item.amount}x"
                tvShoppingItemPrice.text = "${item.price}"
            }
        }
    }
}

class ShoppingItemDiffCallback: DiffUtil.ItemCallback<ShoppingItem>() {
    override fun areItemsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ShoppingItem, newItem: ShoppingItem): Boolean {
        return oldItem == newItem
    }
}