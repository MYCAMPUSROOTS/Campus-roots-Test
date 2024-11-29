package com.example.campusrootsinternapp.enrollment

import androidx.lifecycle.MutableLiveData
import com.example.campusrootsinternapp.base.BaseViewModel
import com.example.campusrootsinternapp.enrollment.model.CompleteEnrollmentRequest
import com.example.campusrootsinternapp.enrollment.model.CompleteEnrollmentResponse
import com.example.campusrootsinternapp.enrollment.model.GenericResponse
import com.example.campusrootsinternapp.enrollment.model.InitiateEnrollmentRequest
import com.example.campusrootsinternapp.repository.AppRepository
import com.example.campusrootsinternapp.util.UseCaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EnrollmentViewModel @Inject constructor(private val appRepository: AppRepository) : BaseViewModel() {
    val enrollment = MutableLiveData<CompleteEnrollmentResponse>()
    val register = MutableLiveData<GenericResponse>()

    fun registerUser(request: InitiateEnrollmentRequest) {
        launch {
            when (val response = appRepository.registerUser(request)) {
                is UseCaseResult.Success -> register.value = response.data.body()
                else -> {}
            }
        }
    }

    fun completeEnrollment(request: CompleteEnrollmentRequest) {
        launch {
            when (val response = appRepository.completeEnrollment(request)) {
                is UseCaseResult.Success -> enrollment.value = response.data.body()
                else -> {}
            }
        }
    }
}