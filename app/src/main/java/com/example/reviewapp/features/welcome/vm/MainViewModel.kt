package com.example.reviewapp.features.welcome.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.reviewapp.features.welcome.domain.GetListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: GetListUseCase):ViewModel() {
    var text : String = ""

    private val testMutableLiveData:MutableLiveData<String> = MutableLiveData()
    val testLiveData:LiveData<String> get() = testMutableLiveData

    fun test() {
        viewModelScope.launch {

            var result = useCase.execute()

             result.getOrNull()?.let { testMutableLiveData.value = it }
        }
    }
}