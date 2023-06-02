package com.example.reviewapp.usecase

import com.example.reviewapp.usecase.repository.PhotoRepository
import com.example.reviewapp.domain.Photo
import javax.inject.Inject

class GetFavoritePhotosUseCase @Inject constructor(private val repository: PhotoRepository) {

    suspend fun execute():Result<List<Photo>>{
            return repository.loadFavoritePhotos()
    }
}