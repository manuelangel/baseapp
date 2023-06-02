package com.example.reviewapp.data.datasource.impl

import com.example.reviewapp.data.datasource.PhotoNetworkDatasource
import com.example.reviewapp.data.datasource.api.PhotosApi
import com.example.reviewapp.data.datasource.api.RetrofitClient
import com.example.reviewapp.data.datasource.api.entities.response.PhotoResponse
import com.example.reviewapp.data.impl.toPhoto
import com.example.reviewapp.domain.Photo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PhotoNetworkDatasourceImpl:PhotoNetworkDatasource {

    private val api:PhotosApi = RetrofitClient.getInstance().getMyApi()

    override suspend fun loadPhotos():Result<List<Photo>> {
        return try {
            Result.success(api.getPhotos()).map { it.map { photoResponse -> photoResponse.toPhoto() } }
        } catch (e:Exception){
            Result.failure(e)
        }
    }

}