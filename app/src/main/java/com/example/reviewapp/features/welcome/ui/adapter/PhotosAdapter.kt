package com.example.reviewapp.features.welcome.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.reviewapp.base.BaseRvAdapter
import com.example.reviewapp.databinding.ItemPhotoBinding
import com.example.reviewapp.domain.Photo
import com.example.reviewapp.features.welcome.ui.adapter.vh.PhotoViewHolder

class PhotosAdapter: BaseRvAdapter<Photo,PhotoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false).run {
            PhotoViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(items[position])
    }

}