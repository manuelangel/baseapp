package com.example.reviewapp.data

import com.example.reviewapp.domain.Photo

interface PhotoRepository {
    suspend fun test(): Result<List<Photo>>
}