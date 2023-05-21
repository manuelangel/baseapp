package com.example.reviewapp.data.impl


import com.example.reviewapp.data.TestRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class TestRepositoryImpl:TestRepository {

    override suspend fun test():Result<String> {
        return withContext(Dispatchers.IO){
            delay(1000)
            return@withContext Result.success("Hello")
        }
    }
}