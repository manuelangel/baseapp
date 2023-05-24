package com.example.reviewapp.data.datasource.impl

import android.util.Log
import com.example.reviewapp.data.datasource.PhotoDBDatasource
import com.example.reviewapp.data.datasource.localdatabase.entities.PhotoDB
import com.example.reviewapp.data.datasource.localdatabase.realmetities.PhotoRealmDB
import com.example.reviewapp.data.impl.toPhotoDB
import com.example.reviewapp.domain.Photo
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class PhotoDBDatasourceImpl:PhotoDBDatasource {

    private val realmConfig = RealmConfiguration.create(schema = setOf(PhotoRealmDB::class))

    override suspend fun getFavouritePhotos(): Result<List<PhotoDB>> {
        return withContext(Dispatchers.IO){
            lateinit var result:List<PhotoDB>
            val realm = Realm.open(realmConfig)
            try{
                result = realm.query(PhotoRealmDB::class).find().toList().map { it.toPhotoDB() }
            }
            catch (e: Exception){
                return@withContext Result.failure(e)
            }
            finally {
                realm.close()
            }

            return@withContext Result.success(result)
        }
    }

    override suspend fun storeFavoritePhoto(photo: Photo):Result<Boolean> {
        return withContext(Dispatchers.IO){
            val realm = Realm.open(realmConfig)
            try{
                realm.writeBlocking {
                    copyToRealm(PhotoRealmDB().apply {
                        id = photo.id
                        title = photo.title
                        imageUrl = photo.imageUrl
                    })
                }
            }
            catch (e: Exception){
                return@withContext Result.failure(e)
            }
            finally {
                realm.close()
            }

            return@withContext Result.success(true)
        }
    }

    override suspend fun removeFavouritePhoto() {
        TODO("Not yet implemented")
    }
}