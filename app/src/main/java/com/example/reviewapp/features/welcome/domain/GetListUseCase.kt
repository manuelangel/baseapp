package com.example.reviewapp.features.welcome.domain

import com.example.reviewapp.data.TestRepository
import javax.inject.Inject

class GetListUseCase @Inject constructor(private val repository:TestRepository) {

    suspend fun execute():Result<String>{
        return repository.test()
    }
}