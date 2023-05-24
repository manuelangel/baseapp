package com.example.reviewapp.data.datasource.impl

import com.example.reviewapp.data.datasource.TestDatasource
import com.example.reviewapp.data.datasource.api.PhotosApi
import com.example.reviewapp.data.datasource.api.RetrofitClient
import com.example.reviewapp.data.datasource.entities.response.PhotoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class TestDatasourceImpl:TestDatasource {

    private val api:PhotosApi = RetrofitClient.getInstance().getMyApi()

    override suspend fun loadTest():Result<List<PhotoResponse>> {
        return withContext(Dispatchers.IO){
            try {
                return@withContext Result.success(api.getPhotos())
            }
            catch (e:Exception){
                return@withContext Result.failure(e)
            }
        }
    }

}