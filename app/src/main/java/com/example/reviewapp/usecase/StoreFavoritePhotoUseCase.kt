package com.example.reviewapp.usecase

import com.example.reviewapp.usecase.repository.PhotoRepository
import com.example.reviewapp.domain.Photo
import javax.inject.Inject

class StoreFavoritePhotoUseCase @Inject constructor(private val repository: PhotoRepository) {

    suspend fun execute(photo:Photo):Result<Boolean>{
        return repository.storeFavoritePhoto(photo)
    }
}