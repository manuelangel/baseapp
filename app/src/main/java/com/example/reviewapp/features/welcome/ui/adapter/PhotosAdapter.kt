package com.example.reviewapp.features.welcome.ui.adapter

import android.util.SparseIntArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.util.contains
import com.example.reviewapp.base.BaseRvAdapter
import com.example.reviewapp.databinding.ItemPhotoBinding
import com.example.reviewapp.domain.Photo
import com.example.reviewapp.features.welcome.ui.adapter.vh.PhotoViewHolder
import com.example.reviewapp.features.welcome.ui.model.PhotoUI

class PhotosAdapter: BaseRvAdapter<PhotoUI,PhotoViewHolder>() {

    var listener : Listener? = null

    var favoriteItems:SparseIntArray? = null

    private val viewHolderListener = object :PhotoViewHolder.Listener{
        override fun favoriteButtonClicked(photo: Photo, isFavorite: Boolean) {
            listener?.onFavoriteStateChange(photo,isFavorite)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .run {
                PhotoViewHolder(this).apply {
                    listener = viewHolderListener
                }
            }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setFavorites(favorites: SparseIntArray?){
        favoriteItems = favorites
    }

    interface Listener{
        fun onFavoriteStateChange(photo: Photo, isFavorite: Boolean)
    }
}