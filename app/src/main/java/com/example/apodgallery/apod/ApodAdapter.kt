package com.example.apodgallery.apod

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.apodgallery.R
import com.example.apodgallery.databinding.ItemMainBinding
import com.example.apodgallery.model.network.ApodResponse

class ApodAdapter(
    private val onItemClicked: (ApodResponse) -> Unit
) : ListAdapter<ApodResponse, ApodAdapter.ApodViewHolder>(ApodDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApodViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMainBinding.inflate(inflater, parent, false)
        return ApodViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ApodViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ApodViewHolder(private val binding: ItemMainBinding) : ViewHolder(binding.root) {
        fun bind(apodResponse: ApodResponse) {
            binding.apply {
                tvTitle.text = apodResponse.title
                tvDate.text = apodResponse.date
                if (isImageUrl(apodResponse.url)) {
                    Glide.with(root)
                        .load(apodResponse.url)
                        .placeholder(R.drawable.placeholder_image)
                        .error(R.drawable.error_image)
                        .into(imgApod)
                } else {
                    imgApod.setImageResource(R.drawable.error_image)
                }
                root.setOnClickListener {
                    onItemClicked(apodResponse)
                }
            }
        }
    }

    fun isImageUrl(url: String): Boolean {
        val imageExtensions = listOf(".jpg", ".jpeg", ".png", ".bmp", ".gif")
        return imageExtensions.any { url.endsWith(it, ignoreCase = true) }
    }

    companion object ApodDiffCallback : DiffUtil.ItemCallback<ApodResponse>() {
        override fun areItemsTheSame(oldItem: ApodResponse, newItem: ApodResponse): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: ApodResponse, newItem: ApodResponse): Boolean {
            return oldItem == newItem
        }
    }
}