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
            itemPhotoFavStateImage.setOnClickListener {
                val photoSelected = photo
                val isFavoriteState = isFavorite
                if(isFavoriteState !=null && photoSelected != null) {
                    isFavorite = !isFavoriteState
                    /*this@PhotoViewHolder.listener?.favoriteButtonClicked(dataBinding.photo, dataBinding.isFavorite)*/
                }
            }
        }
    }

    override fun bind(item: PhotoUI) {
        dataBinding.itemPhotoImage.loadUrlImage(item.imageUrl)
        dataBinding.itemPhotoText.text = item.title
        dataBinding.photo = item
        dataBinding.isFavorite = false
    }

    interface Listener{
        fun favoriteButtonClicked(photo:Photo,isFavorite:Boolean)
    }

}