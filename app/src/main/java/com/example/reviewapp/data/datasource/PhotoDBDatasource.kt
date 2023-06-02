package com.example.reviewapp.data.datasource

import com.example.reviewapp.domain.Photo

interface PhotoDBDatasource {

    suspend fun getFavouritePhotos(): Result<List<Photo>>
    suspend fun storeFavoritePhoto(photo: Photo):Result<Boolean>
    suspend fun removeFavouritePhoto(photoId: Int):Result<Boolean>

}