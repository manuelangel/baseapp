package com.example.reviewapp.features.welcome.ui.model

import android.util.SparseIntArray
import androidx.core.util.contains
import com.example.reviewapp.domain.Photo

data class PhotoUI(val id:Int, val title:String, val imageUrl:String, var isFavorite:Boolean)

fun Photo.toPhotoUI(favoritePhotoIds: SparseIntArray?) =
    PhotoUI(id, title, imageUrl, favoritePhotoIds?.contains(id) ?: false)

fun PhotoUI.toPhoto() = Photo(id,title,imageUrl)
