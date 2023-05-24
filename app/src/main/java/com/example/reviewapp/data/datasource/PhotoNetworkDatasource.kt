package com.example.reviewapp.data.datasource

import com.example.reviewapp.data.datasource.entities.response.PhotoResponse

interface PhotoNetworkDatasource {
    suspend fun loadPhotos(): Result<List<PhotoResponse>>
}