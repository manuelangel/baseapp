package com.example.reviewapp.data.datasource

import com.example.reviewapp.data.datasource.localdatabase.entities.PhotoDB
import com.example.reviewapp.domain.Photo

interface PhotoDBDatasource {

    suspend fun getFavouritePhotos(): Result<List<PhotoDB>>
    suspend fun storeFavoritePhoto(photo: Photo):Result<Boolean>
    suspend fun removeFavouritePhoto()

}