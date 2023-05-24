package com.example.reviewapp.features.welcome.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reviewapp.domain.Photo
import com.example.reviewapp.features.welcome.usecase.GetFavoritePhotosUseCase
import com.example.reviewapp.features.welcome.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: GetPhotosUseCase,private val favoritePhotosUseCase: GetFavoritePhotosUseCase):ViewModel() {

    private val photosMutableLiveData:MutableLiveData<Result<List<Photo>>> = MutableLiveData()
    val photosLiveData:LiveData<Result<List<Photo>>> get() = photosMutableLiveData
    private val loadingStateMutableLiveData:MutableLiveData<Boolean> = MutableLiveData(false)
    val loadingStateLiveData:LiveData<Boolean> get() = loadingStateMutableLiveData

    fun loadPhotos() {
        viewModelScope.launch {
            changeToLoadingState()
            useCase.execute().let { photosMutableLiveData.value = it }
            changeToNonLoadingState()
        }
    }

    fun loadFavoritePhotos() {
        viewModelScope.launch {
            changeToLoadingState()
            favoritePhotosUseCase.execute().let { photosMutableLiveData.value = it }
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