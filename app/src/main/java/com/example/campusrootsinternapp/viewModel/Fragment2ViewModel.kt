package com.example.campusrootsinternapp.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.campusrootsinternapp.base.BaseViewModel
import com.example.campusrootsinternapp.model.PostResponse
import com.example.campusrootsinternapp.repository.AppRepository
import com.example.campusrootsinternapp.util.SingleLiveEvent
import com.example.campusrootsinternapp.util.UseCaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class Fragment2ViewModel @Inject constructor(
    private val appRepository: AppRepository
): BaseViewModel() {

    val postResponse = SingleLiveEvent<PostResponse>()
    val showLoader = MutableLiveData<Boolean>()
    val errorObserver = SingleLiveEvent<String>()

    fun getPost(postId: Int) {

        showLoader.value = true

        launch {

            when (val response = appRepository.getPost(postId)) {
                is UseCaseResult.Success -> postResponse.value = response.data.body()
                else -> errorObserver.value = "There was an error"
            }

            showLoader.postValue(false)
        }
    }

}