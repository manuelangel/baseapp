package com.example.reviewapp.features.welcome.usecase

import com.example.reviewapp.data.PhotoRepository
import com.example.reviewapp.domain.Photo
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(private val repository:PhotoRepository) {

    suspend fun execute():Result<List<Photo>>{
        return repository.loadPhotos()
    }

}