package com.example.reviewapp.features.welcome.usecase

import com.example.reviewapp.data.TestRepository
import com.example.reviewapp.domain.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetListUseCase @Inject constructor(private val repository:TestRepository) {

    suspend fun execute():Result<List<Photo>>{
        return repository.test()
    }
}