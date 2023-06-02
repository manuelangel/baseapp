package com.example.reviewapp.data.impl


import com.example.reviewapp.usecase.repository.PhotoRepository
import com.example.reviewapp.data.datasource.PhotoDBDatasource
import com.example.reviewapp.data.datasource.PhotoNetworkDatasource
import com.example.reviewapp.domain.Photo
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val photoNetworkDatasource: PhotoNetworkDatasource,
    private val photoDBDatasource: PhotoDBDatasource
): PhotoRepository {

    override suspend fun loadPhotos():Result<List<Photo>> {
        return photoNetworkDatasource.loadPhotos()
    }

    override suspend fun loadFavoritePhotos(): Result<List<Photo>> {
        return photoDBDatasource.getFavouritePhotos()
    }

    override suspend fun storeFavoritePhoto(photo:Photo): Result<Boolean> {
        return photoDBDatasource.storeFavoritePhoto(photo)
    }

    override suspend fun removeFavoritePhoto(photoId: Int): Result<Boolean> {
        return photoDBDatasource.removeFavouritePhoto(photoId)
    }
}