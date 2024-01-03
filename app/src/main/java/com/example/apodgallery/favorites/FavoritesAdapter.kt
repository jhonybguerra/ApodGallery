package com.example.apodgallery.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.apodgallery.databinding.ItemFavoriteBinding
import com.example.apodgallery.model.db.FavoriteApod

class FavoritesAdapter(
    private val onItemClicked: (FavoriteApod) -> Unit
) : ListAdapter<FavoriteApod, FavoritesAdapter.FavoritesViewHolder>(FavoritesDiffCallback)  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritesViewHolder {
        return FavoritesViewHolder(
            ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavoritesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class FavoritesViewHolder(private val binding: ItemFavoriteBinding) : ViewHolder(binding.root) {
        fun bind(favoriteApod: FavoriteApod) {
            binding.apply {
                tvFavoriteTitle.text = favoriteApod.title
                tvFavoriteDate.text = favoriteApod.date
                Glide.with(root)
                    .load(favoriteApod.url)
                    .into(imgApod)
                root.setOnClickListener {
                    onItemClicked(
                        favoriteApod
                    )
                }
            }
        }
    }

    companion object FavoritesDiffCallback : DiffUtil.ItemCallback<FavoriteApod>() {
        override fun areItemsTheSame(oldItem: FavoriteApod, newItem: FavoriteApod): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FavoriteApod, newItem: FavoriteApod): Boolean {
            return oldItem == newItem
        }
    }
}