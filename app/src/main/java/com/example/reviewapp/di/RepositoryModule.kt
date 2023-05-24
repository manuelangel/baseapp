package com.example.reviewapp.di

import com.example.reviewapp.data.PhotoRepository
import com.example.reviewapp.data.datasource.PhotoNetworkDatasource
import com.example.reviewapp.data.impl.PhotoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun providePhotosRepository(photoNetworkDatasource: PhotoNetworkDatasource): PhotoRepository {
        return PhotoRepositoryImpl(photoNetworkDatasource)
    }
}