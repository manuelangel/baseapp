package com.example.reviewapp.features.welcome.vm

import android.util.Log
import android.util.SparseIntArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reviewapp.domain.Photo
import com.example.reviewapp.features.welcome.ui.model.PhotoUI
import com.example.reviewapp.features.welcome.ui.model.toPhoto
import com.example.reviewapp.features.welcome.ui.model.toPhotoUI
import com.example.reviewapp.usecase.GetFavoritePhotosUseCase
import com.example.reviewapp.usecase.GetPhotosUseCase
import com.example.reviewapp.usecase.RemoveFavoritePhotoUseCase
import com.example.reviewapp.usecase.StoreFavoritePhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val useCase: GetPhotosUseCase,
    private val getFavoritePhotosUseCase: GetFavoritePhotosUseCase,
    private val addFavoritePhotosUseCase: StoreFavoritePhotoUseCase,
    private val removeFavoritePhotoUseCase: RemoveFavoritePhotoUseCase
) : ViewModel() {

    private val photosMutableLiveData: MutableLiveData<List<PhotoUI>> = MutableLiveData()
    val photosLiveData: LiveData<List<PhotoUI>> get() = photosMutableLiveData
    private val loadingStateMutableLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val loadingStateLiveData: LiveData<Boolean> get() = loadingStateMutableLiveData
    private val updateFavoritePhotoResultMutableLiveData: MutableLiveData<Boolean> =
        MutableLiveData()
    val updateFavoritePhotoResultLiveData: LiveData<Boolean> get() = loadingStateMutableLiveData
    private val favoritePhotosMutableLiveData: MutableLiveData<SparseIntArray> = MutableLiveData()
    val favoritePhotosLiveData: LiveData<SparseIntArray> get() = favoritePhotosMutableLiveData

    fun loadPhotos() {
        viewModelScope.launch(Dispatchers.Main) {

            changeToLoadingState()
            var result: Result<List<Photo>>?
            var favorites: SparseIntArray?
            withContext(Dispatchers.IO) {
                favorites = getFavoritePhotosUseCase.execute().getOrNull()?.let { favoritePhotos ->
                    SparseIntArray().apply {
                        favoritePhotos.forEach { photo ->
                            put(photo.id, photo.id)
                        }
                    }
                }
                result = useCase.execute()
            }
            result?.let {
                if (it.isSuccess) {
                    photosMutableLiveData.value = mapPhotosToPhotoUI(it.getOrNull(), favorites)
                }
                else{
                    Log.i("TEST_MANU","Error loadPhotos")
                }
            }

            changeToNonLoadingState()
        }
    }

    private fun mapPhotosToPhotoUI(
        photos: List<Photo>?,
        favorites: SparseIntArray?
    ): List<PhotoUI> =
        photos?.map { it.toPhotoUI(favorites) }?: mutableListOf()

    fun savePhotoAsFavorite(photo: PhotoUI) {
        viewModelScope.launch(Dispatchers.Main) {
            changeToLoadingState()
            var result:Result<Boolean>?
            withContext(Dispatchers.IO){
                result = addFavoritePhotosUseCase.execute(photo.toPhoto())
            }
            result?.let {
                if(it.isSuccess){
                    updateFavoritePhotoResultMutableLiveData.value= it.getOrNull()?:false
                }
                else{
                    Log.i("TEST_MANU","Error savePhotoAsFavorite")
                }
            }
            changeToNonLoadingState()
        }
    }

    fun removePhotoFromFavorites(photoId: Int) {
        viewModelScope.launch(Dispatchers.Main) {
            changeToLoadingState()
            var result:Result<Boolean>?
            withContext(Dispatchers.IO){
                result = removeFavoritePhotoUseCase.execute(photoId)
            }
            result?.let {
                if(it.isSuccess){
                    updateFavoritePhotoResultMutableLiveData.value= it.getOrNull()?:false
                }
                else{
                    Log.i("TEST_MANU","Error removePhotoFromFavorites")
                }
            }
            changeToNonLoadingState()
        }
    }


    private fun changeToLoadingState() {
        loadingStateMutableLiveData.value = true
    }

    private fun changeToNonLoadingState() {
        loadingStateMutableLiveData.value = false
    }
}