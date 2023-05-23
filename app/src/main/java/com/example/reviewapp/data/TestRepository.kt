package com.example.reviewapp.data

import com.example.reviewapp.domain.Photo

interface TestRepository {
    suspend fun test(): Result<List<Photo>>
}