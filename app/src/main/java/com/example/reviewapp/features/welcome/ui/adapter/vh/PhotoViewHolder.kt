package com.example.reviewapp.features.welcome.ui.adapter.vh

import android.util.Log
import com.example.reviewapp.base.BaseViewHolder
import com.example.reviewapp.base.loadUrlImage
import com.example.reviewapp.databinding.ItemPhotoBinding
import com.example.reviewapp.domain.Photo
import com.example.reviewapp.features.welcome.ui.model.PhotoUI

class PhotoViewHolder(private val dataBinding: ItemPhotoBinding) : BaseViewHolder<PhotoUI>(dataBinding.root) {

    var listener:Listener? = null

    init {
        dataBinding.apply {
            itemPhotoFavStateImage.setOnClickListener { _ ->
                val photoSelected = photo
                photoSelected?.let {
                    it.isFavorite = !it.isFavorite
                    this@PhotoViewHolder.listener?.favoriteButtonClicked(it)
                    photo = it
                }
            }
        }
    }

    override fun bind(item: PhotoUI) {
        dataBinding.itemPhotoImage.loadUrlImage(item.imageUrl)
        dataBinding.itemPhotoText.text = item.title
        dataBinding.photo = item
    }

    interface Listener{
        fun favoriteButtonClicked(photo:PhotoUI)
    }

}