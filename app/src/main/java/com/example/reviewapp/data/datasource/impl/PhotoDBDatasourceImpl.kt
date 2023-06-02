package com.example.reviewapp.data.datasource.impl

import com.example.reviewapp.data.datasource.PhotoDBDatasource
import com.example.reviewapp.data.datasource.localdatabase.realmetities.PhotoRealmDB
import com.example.reviewapp.data.impl.toPhoto
import com.example.reviewapp.domain.Photo
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.UpdatePolicy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PhotoDBDatasourceImpl : PhotoDBDatasource {

    private val realmConfig = RealmConfiguration.create(schema = setOf(PhotoRealmDB::class))

    override suspend fun getFavouritePhotos(): Result<List<Photo>> {
        return withContext(Dispatchers.IO) {
            lateinit var result: List<Photo>
            val realm = Realm.open(realmConfig)
            try {
                result = realm.query(PhotoRealmDB::class).find().toList().map { it.toPhoto() }
            } catch (e: Exception) {
                return@withContext Result.failure(e)
            } finally {
                realm.close()
            }

            return@withContext Result.success(result)
        }
    }

    override suspend fun storeFavoritePhoto(photo: Photo): Result<Boolean> {
        val realm = Realm.open(realmConfig)
        try {
            realm.writeBlocking {

                copyToRealm(PhotoRealmDB().apply {
                    id = photo.id
                    title = photo.title
                    imageUrl = photo.imageUrl
                },UpdatePolicy.ALL)
            }
        } catch (e: Exception) {
            return Result.failure(e)
        } finally {
            realm.close()
        }

        return Result.success(true)
    }

    override suspend fun removeFavouritePhoto(photoId: Int):Result<Boolean> {
        val realm = Realm.open(realmConfig)
        try {
           val foundResult = realm.query(PhotoRealmDB::class,"id == $photoId").find()
            realm.write {
                findLatest(foundResult.first())?.let {
                    delete(it)
                }
            }
        } catch (e: Exception) {
            return Result.failure(e)
        } finally {
            realm.close()
        }

        return Result.success(true)
    }
}