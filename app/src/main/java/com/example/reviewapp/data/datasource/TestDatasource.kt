package com.example.reviewapp.data.datasource

import com.example.reviewapp.data.datasource.entities.response.PhotoResponse
import com.example.reviewapp.data.datasource.entities.response.PhotosResponse

interface TestDatasource {
    suspend fun loadTest(): Result<List<PhotoResponse>>
}