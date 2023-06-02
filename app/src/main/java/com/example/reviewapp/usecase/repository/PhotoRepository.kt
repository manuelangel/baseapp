package com.example.reviewapp.usecase.repository

import com.example.reviewapp.domain.Photo

interface PhotoRepository {
    suspend fun loadPhotos(): Result<List<Photo>>
    suspend fun loadFavoritePhotos():Result<List<Photo>>
    suspend fun storeFavoritePhoto(photo: Photo): Result<Boolean>
    suspend fun removeFavoritePhoto(photoId: Int): Result<Boolean>
}