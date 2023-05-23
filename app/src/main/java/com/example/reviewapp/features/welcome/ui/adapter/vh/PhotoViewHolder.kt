package com.example.reviewapp.features.welcome.ui.adapter.vh

import android.util.Log
import com.example.reviewapp.base.BaseViewHolder
import com.example.reviewapp.base.loadUrlImage
import com.example.reviewapp.databinding.ItemPhotoBinding
import com.example.reviewapp.domain.Photo

class PhotoViewHolder(private val dataBinding: ItemPhotoBinding) : BaseViewHolder<Photo>(dataBinding.root) {

    override fun bind(item: Photo) {
        dataBinding.itemPhotoImage.loadUrlImage(item.imageUrl)
        dataBinding.itemPhotoText.text = item.title
    }

}