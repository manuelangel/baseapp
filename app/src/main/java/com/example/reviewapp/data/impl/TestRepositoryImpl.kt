package com.example.reviewapp.data.impl


import com.example.reviewapp.data.TestRepository
import com.example.reviewapp.data.datasource.TestDatasource
import com.example.reviewapp.domain.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TestRepositoryImpl @Inject constructor(private val testDatasource: TestDatasource):
    TestRepository {

    override suspend fun test():Result<List<Photo>> {
        return testDatasource.loadTest().map { it.map { photoResponse -> photoResponse.toPhoto() } }
    }
}