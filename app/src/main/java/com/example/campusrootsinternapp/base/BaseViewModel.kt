package com.example.campusrootsinternapp.base

import androidx.lifecycle.*
import com.example.campusrootsinternapp.util.SingleLiveEvent
import com.example.campusrootsinternapp.util.UseCaseResult
import com.example.campusrootsinternapp.util.handleException
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlin.coroutines.CoroutineContext

open class BaseViewModel:ViewModel(), CoroutineScope,LifecycleObserver {
    // Coroutine's background job
    private val job = Job()
    // Define default thread for Coroutine as Main and add job
    override val coroutineContext: CoroutineContext = Dispatchers.Main + job
    val showLoading = MutableLiveData<Boolean>()
    val showError = SingleLiveEvent<String>()

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun <R:Any,T:Any> apiRequest(
        request:R, apiCall:suspend (request:R)-> UseCaseResult<T>, observer: SingleLiveEvent<T>, getError: (response: T) -> String,
        displayLoading:Boolean = true, showErrorMessage:Boolean = true,
        onErrorObserver: SingleLiveEvent<Unit>? = null,
        customLoader: SingleLiveEvent<Boolean>? = null){
        if(displayLoading)  {
            customLoader?.let {
                it.value = true
            } ?: run {
                showLoading.value = true
            }
        }

        launch {
            val response = withContext(IO){
                apiCall(request)
            }
            if(displayLoading){
                customLoader?.let {
                    it.value = false
                } ?: run {
                    showLoading.value = false
                }

            }
            when(response){
                is UseCaseResult.Success -> {
                    observer.value = response.data
                }
                is UseCaseResult.FailedAPI -> {
                    if(showErrorMessage) showError.value = getError(response.data)
                    onErrorObserver?.call()
                }
                is UseCaseResult.SessionTimeOut -> {
                    if(showErrorMessage) showError.value = response.errorMessage
                    onErrorObserver?.call()
                }
                is UseCaseResult.ErrorAPI -> {
                    if (response.data !=null ){
                        if(showErrorMessage) showError.value = getError(response.data)
                    }else{
                        if(showErrorMessage) showError.value = response.exception.handleException()
                    }
                    onErrorObserver?.call()
                }
                is UseCaseResult.Error -> {
                    if(showErrorMessage) showError.value = response.exception.handleException()
                    onErrorObserver?.call()
                }
                else -> {}
            }
        }
    }

    fun <R : Any, P : Any, T : Any> apiRequestWithParam(
        request: R,
        param: P,
        apiCall: suspend (request: R, param: P)-> UseCaseResult<T>,
        observer: SingleLiveEvent<T>,
        getError: (response: T) -> String,
        displayLoading: Boolean = true,
        showErrorMessage: Boolean = true,
        onErrorObserver: SingleLiveEvent<Unit>? = null,
        customErrorObserver: SingleLiveEvent<String>? = null,
        customLoader: SingleLiveEvent<Boolean>? = null
    ) {
        if (displayLoading) {
            customLoader?.let {
                it.value = true
            } ?: run {
                showLoading.value = true
            }
        }

        launch {
            val response = withContext(IO) {
                apiCall(request, param ) // Pass both param and request
            }
            if (displayLoading) {
                customLoader?.let {
                    it.value = false
                } ?: run {
                    showLoading.value = false
                }
            }
            when (response) {
                is UseCaseResult.Success -> {
                    observer.value = response.data
                }
                is UseCaseResult.FailedAPI -> {
                    if (showErrorMessage) showError.value = getError(response.data)
                    onErrorObserver?.call()
                }
                is UseCaseResult.SessionTimeOut -> {
                    if (showErrorMessage) showError.value = response.errorMessage
                    onErrorObserver?.call()
                }
                is UseCaseResult.ErrorAPI -> {
                    if (response.data != null) {
                        if (showErrorMessage) showError.value = getError(response.data)
                    } else {
                        if (showErrorMessage) showError.value = response.exception.handleException()
                    }
                    onErrorObserver?.call()
                }
                is UseCaseResult.Error ->{
                    if (showErrorMessage) showError.value = response.exception.handleException()
                    onErrorObserver?.call()
                }
                else -> {}
            }
        }
    }

    fun <R:Any,T:Any> apiRequestOnSuccessOperation(
        request:R, apiCall:suspend (request:R)-> UseCaseResult<T>, observer: SingleLiveEvent<T>, getError: (response: T) -> String,
        displayLoading:Boolean = true, showErrorMessage:Boolean = true, onSuccessOperations: (response:T)->Unit,
        customErrorObserver: SingleLiveEvent<String>? = null,
        onErrorObserver: SingleLiveEvent<Unit>? = null,
        customLoader: SingleLiveEvent<Boolean>? = null){
        if(displayLoading)  {
            customLoader?.let {
                it.value = true
            } ?: run {
                showLoading.value = true
            }
        }

        launch {
            val response = withContext(IO){
                apiCall(request)
            }
            if(displayLoading){
                customLoader?.let {
                    it.value = false
                } ?: run {
                    showLoading.value = false
                }

            }
            when(response){
                is UseCaseResult.Success -> {
                    onSuccessOperations(response.data)
                    observer.value = response.data
                }
                is UseCaseResult.SessionTimeOut -> {
                    if (showErrorMessage) showError.value = response.errorMessage
                    onErrorObserver?.call()
                }
                is UseCaseResult.FailedAPI -> {
                    if(showErrorMessage) showError.value = getError(response.data)

                    onErrorObserver?.call()
                    customErrorObserver?.value = getError(response.data)
                }
                is UseCaseResult.ErrorAPI -> {
                    if (response.data !=null ){
                        if(showErrorMessage) showError.value = getError(response.data)
                    }else{
                        if(showErrorMessage) showError.value = response.exception.handleException()
                        customErrorObserver?.value = response.exception.handleException()
                    }
                    onErrorObserver?.call()
                }
                is UseCaseResult.Error -> {
                    if(showErrorMessage) showError.value = response.exception.handleException()
                    onErrorObserver?.call()
                    customErrorObserver?.value = response.exception.handleException()
                }
                else -> {}
            }
        }
    }

    fun <R:Any, P : Any, T:Any> apiRequestOnSuccessOperationWithParams(
        request: R,
        param: P,
        apiCall: suspend (request: R, param: P)-> UseCaseResult<T>,
        observer: SingleLiveEvent<T>, getError: (response: T) -> String,
        displayLoading:Boolean = true, showErrorMessage:Boolean = true, onSuccessOperations: (response:T)->Unit,
        onErrorObserver: SingleLiveEvent<Unit>? = null,
        customErrorObserver: SingleLiveEvent<String>? = null,
        customLoader: SingleLiveEvent<Boolean>? = null){
        if(displayLoading)  {
            customLoader?.let {
                it.value = true
            } ?: run {
                showLoading.value = true
            }
        }

        launch {
            val response = withContext(IO){
                apiCall(request, param )
            }
            if(displayLoading){
                customLoader?.let {
                    it.value = false
                } ?: run {
                    showLoading.value = false
                }

            }
            when(response){
                is UseCaseResult.Success -> {
                    onSuccessOperations(response.data)
                    observer.value = response.data
                }
                is UseCaseResult.FailedAPI -> {
                    if(showErrorMessage) showError.value = getError(response.data)
                    customErrorObserver?.value = getError(response.data)
                    onErrorObserver?.call()
                }
                is UseCaseResult.ErrorAPI -> {
                    if (response.data !=null ){
                        if(showErrorMessage) showError.value = getError(response.data)
                    }else{
                        if(showErrorMessage) showError.value = response.exception.handleException()
                        customErrorObserver?.value = response.exception.handleException()
                    }
                    onErrorObserver?.call()
                }
                is UseCaseResult.Error -> {
                    if(showErrorMessage) showError.value = response.exception.handleException()
                    onErrorObserver?.call()
                    customErrorObserver?.value = response.exception.handleException()
                }
                else -> {}
            }
        }
    }

    fun <T:Any> getApiRequest(apiCall:suspend ()-> UseCaseResult<T>, observer:SingleLiveEvent<T>, getError: (response: T) -> String,
                              displayLoading:Boolean = true, showErrorMessage:Boolean = true,
                              onErrorObserver:SingleLiveEvent<Unit>? = null){
        if(displayLoading)  showLoading.value = true
        launch {
            val response = withContext(IO){apiCall()}
            if(displayLoading)   showLoading.value = false
            when(response){
                is UseCaseResult.Success -> observer.value = response.data
                is UseCaseResult.FailedAPI -> {
                    if(showErrorMessage) showError.value = getError(response.data)
                    onErrorObserver?.call()
                }
                is UseCaseResult.ErrorAPI -> {
                    if (response.data !=null ){
                        if(showErrorMessage) showError.value = getError(response.data)
                    }else{
                        if(showErrorMessage) showError.value = response.exception.handleException()
                    }
                    onErrorObserver?.call()
                }
                is UseCaseResult.Error -> {
                    if(showErrorMessage) showError.value = response.exception.handleException()
                    onErrorObserver?.call()
                }
                else -> {}
            }
        }
    }

}