package com.example.reviewapp.data.impl


import com.example.reviewapp.data.PhotoRepository
import com.example.reviewapp.data.datasource.PhotoNetworkDatasource
import com.example.reviewapp.domain.Photo
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(private val photoNetworkDatasource: PhotoNetworkDatasource):
    PhotoRepository {

    override suspend fun test():Result<List<Photo>> {
        return photoNetworkDatasource.loadPhotos().map { it.map { photoResponse -> photoResponse.toPhoto() } }
    }
}