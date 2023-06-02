package com.example.reviewapp.di

import com.example.reviewapp.usecase.repository.PhotoRepository
import com.example.reviewapp.data.datasource.PhotoDBDatasource
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
    fun providePhotosRepository(photoNetworkDatasource: PhotoNetworkDatasource,photoDBDatasource: PhotoDBDatasource): PhotoRepository {
        return PhotoRepositoryImpl(photoNetworkDatasource,photoDBDatasource)
    }
}