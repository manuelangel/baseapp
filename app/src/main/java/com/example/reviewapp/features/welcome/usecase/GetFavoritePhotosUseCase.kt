package com.example.reviewapp.features.welcome.usecase

import com.example.reviewapp.data.PhotoRepository
import com.example.reviewapp.domain.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetFavoritePhotosUseCase @Inject constructor(private val repository: PhotoRepository) {

    suspend fun execute():Result<List<Photo>>{
            return repository.loadFavoritePhotos()
    }
}