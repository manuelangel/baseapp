package com.example.reviewapp.data.datasource.api

import com.example.reviewapp.base.UnsafeOkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient private constructor() {

    companion object {
        private lateinit var instance: RetrofitClient

        @Synchronized
        fun getInstance(): RetrofitClient {
            if (!this::instance.isInitialized) {
                instance = RetrofitClient()
            }
            return instance
        }
    }
    private val BASE_URL = "https://jsonplaceholder.typicode.com"
    private var myApi: PhotosApi

    init {
        val client = UnsafeOkHttpClient.getUnsafeOkHttpClient()
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        myApi = retrofit.create(PhotosApi::class.java)
    }

    fun getMyApi(): PhotosApi {
        return myApi
    }
}