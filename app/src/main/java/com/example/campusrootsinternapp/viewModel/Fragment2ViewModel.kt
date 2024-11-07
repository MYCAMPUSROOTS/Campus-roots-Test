package com.example.campusrootsinternapp.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.campusrootsinternapp.ApiService
import com.example.campusrootsinternapp.base.BaseViewModel
import com.example.campusrootsinternapp.model.PostResponse
import com.example.campusrootsinternapp.repository.AppRepository
import com.example.campusrootsinternapp.util.SingleLiveEvent
import com.example.campusrootsinternapp.util.UseCaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Fragment2ViewModel @Inject constructor(
    private val appRepository: AppRepository
): BaseViewModel() {

    val postResponse = SingleLiveEvent<PostResponse>()
    val showLoader = MutableLiveData<Boolean>()

    fun getPosts(){
        showLoader.value = true
        launch {
            when(val response = appRepository.getPosts()){
                is UseCaseResult.Success -> {
                    postResponse.value = response.data.body()
                }
                else -> {}
            }

            showLoader.postValue(false)
        }
    }

    init {
        getPosts()
    }
}