package com.example.reviewapp.data

import com.example.reviewapp.domain.Photo

interface PhotoRepository {
    suspend fun loadPhotos(): Result<List<Photo>>
    suspend fun loadFavoritePhotos():Result<List<Photo>>
    suspend fun storeFavoritePhoto(photo: Photo): Result<Boolean>
}