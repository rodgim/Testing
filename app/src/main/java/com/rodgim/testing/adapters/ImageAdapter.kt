package com.rodgim.testing.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.rodgim.testing.databinding.ItemImageBinding
import javax.inject.Inject

class ImageAdapter @Inject constructor(
    private val glide: RequestManager
) : ListAdapter<String, ImageAdapter.ImageViewHolder>(ImageDiffCallback()){

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = currentList[position]
        holder.bind(url, glide)
    }

    class ImageViewHolder(
        private val view: ItemImageBinding,
        private val listener: ((String) -> Unit)? = null
    ): RecyclerView.ViewHolder(view.root) {
        fun bind(url: String, glide: RequestManager) {
            view.apply {
                glide.load(url).into(ivShoppingImage)

                root.setOnClickListener {
                    listener?.let { click ->
                        click(url)
                    }
                }
            }
        }
    }

}

class ImageDiffCallback: DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}