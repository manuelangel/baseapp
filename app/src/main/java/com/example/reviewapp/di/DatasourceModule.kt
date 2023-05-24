package com.example.reviewapp.di

import com.example.reviewapp.data.datasource.PhotoDBDatasource
import com.example.reviewapp.data.datasource.PhotoNetworkDatasource
import com.example.reviewapp.data.datasource.impl.PhotoDBDatasourceImpl
import com.example.reviewapp.data.datasource.impl.PhotoNetworkDatasourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatasourceModule {

    @Provides
    fun providePhotosNetworkDatasource(): PhotoNetworkDatasource {
        return PhotoNetworkDatasourceImpl()
    }

    @Provides
    fun providePhotosDBDatasource(): PhotoDBDatasource {
        return PhotoDBDatasourceImpl()
    }
}