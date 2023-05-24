package com.example.reviewapp.features.welcome.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reviewapp.domain.Photo
import com.example.reviewapp.features.welcome.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: GetPhotosUseCase):ViewModel() {
    var text : String = ""

    private val testMutableLiveData:MutableLiveData<List<Photo>> = MutableLiveData()
    val testLiveData:LiveData<List<Photo>> get() = testMutableLiveData
    private val loadingStateMutableLiveData:MutableLiveData<Boolean> = MutableLiveData(false)
    val loadingStateLiveData:LiveData<Boolean> get() = loadingStateMutableLiveData

    fun test() {
        viewModelScope.launch {
            changeToLoadingState()
            useCase.execute().getOrNull()?.let { testMutableLiveData.value = it }
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