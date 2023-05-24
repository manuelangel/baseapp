package com.example.reviewapp.data.datasource.api

import com.example.reviewapp.data.datasource.entities.response.PhotoResponse
import retrofit2.http.GET

interface PhotosApi {

    @GET("/photos")
    suspend fun getPhotos():List<PhotoResponse>
}