package com.example.reviewapp.data.datasource

import com.example.reviewapp.domain.Photo

interface PhotoNetworkDatasource {
    suspend fun loadPhotos(): Result<List<Photo>>
}