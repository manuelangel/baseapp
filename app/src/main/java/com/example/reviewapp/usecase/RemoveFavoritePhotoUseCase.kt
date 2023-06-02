package com.example.reviewapp.usecase

import com.example.reviewapp.usecase.repository.PhotoRepository
import com.example.reviewapp.domain.Photo
import javax.inject.Inject

class RemoveFavoritePhotoUseCase @Inject constructor(private val repository: PhotoRepository) {

    suspend fun execute(photoId:Int):Result<Boolean>{
        return repository.removeFavoritePhoto(photoId)
    }
}