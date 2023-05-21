package com.example.reviewapp.data

interface TestRepository {
    suspend fun test():Result<String>
}