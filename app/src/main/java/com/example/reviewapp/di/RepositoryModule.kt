package com.example.reviewapp.di

import com.example.reviewapp.data.TestRepository
import com.example.reviewapp.data.impl.TestRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun getTestRepository(): TestRepository {
        return TestRepositoryImpl()
    }
}