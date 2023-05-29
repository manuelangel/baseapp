package com.example.reviewapp.features.welcome.vm

import android.util.SparseArray
import android.util.SparseIntArray
import androidx.core.util.contains
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reviewapp.domain.Photo
import com.example.reviewapp.features.welcome.ui.model.PhotoUI
import com.example.reviewapp.features.welcome.usecase.GetFavoritePhotosUseCase
import com.example.reviewapp.features.welcome.usecase.GetPhotosUseCase
import com.example.reviewapp.features.welcome.usecase.StoreFavoritePhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: GetPhotosUseCase,private val getFavoritePhotosUseCase: GetFavoritePhotosUseCase,private val addFavoritePhotosUseCase: StoreFavoritePhotoUseCase):ViewModel() {

    private val photosMutableLiveData:MutableLiveData<List<PhotoUI>> = MutableLiveData()
    val photosLiveData:LiveData<List<PhotoUI>> get() = photosMutableLiveData
    private val loadingStateMutableLiveData:MutableLiveData<Boolean> = MutableLiveData(false)
    val loadingStateLiveData:LiveData<Boolean> get() = loadingStateMutableLiveData
    private val updateFavoritePhotoResultMutableLiveData:MutableLiveData<Boolean> = MutableLiveData()
    val updateFavoritePhotoResultLiveData:LiveData<Boolean> get() = loadingStateMutableLiveData
    private val favoritePhotosMutableLiveData:MutableLiveData<SparseIntArray> = MutableLiveData()
    val favoritePhotosLiveData:LiveData<SparseIntArray> get() = favoritePhotosMutableLiveData

    fun loadPhotos() {
        viewModelScope.launch {
            changeToLoadingState()
            val favorites: SparseIntArray? = getFavoritePhotosUseCase.execute().getOrNull()?.let { favoritePhotos ->
                SparseIntArray().apply {
                    favoritePhotos.forEach { photo ->
                        put(photo.id,photo.id)
                    }
                }
            }
            useCase.execute().let { photosMutableLiveData.value = mapPhotosToPhotoUI(it.getOrNull(),favorites) }
            changeToNonLoadingState()
        }
    }

    private fun mapPhotosToPhotoUI(
        photos: List<Photo>?,
        favorites: SparseIntArray?
    ): List<PhotoUI>? {
        return photos?.map { PhotoUI(it.id,it.title,it.imageUrl, favorites?.contains(it.id) ?:false) }
    }

    fun savePhotoAsFavorite(photo:Photo){
        viewModelScope.launch {
            changeToLoadingState()
            addFavoritePhotosUseCase.execute(photo).let { updateFavoritePhotoResultMutableLiveData.value = it.getOrNull() }
            changeToNonLoadingState()
        }
    }

    fun removePhotoFromFavorites(photoId:Photo){
        viewModelScope.launch {
            changeToLoadingState()
            //addFavoritePhotosUseCase.execute(photo).let { updateFavoritePhotoResultMutableLiveData.value = it.getOrNull() }
            changeToNonLoadingState()
        }
    }


    private fun changeToLoadingState(){
        loadingStateMutableLiveData.value = true
    }

    private fun changeToNonLoadingState(){
        loadingStateMutableLiveData.value = false
    }
}