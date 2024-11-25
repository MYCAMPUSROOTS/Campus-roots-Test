package com.example.campusrootsinternapp.enrollment.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.campusrootsinternapp.enrollment.data.EnrollmentRepository
import com.example.campusrootsinternapp.enrollment.data.model.CompleteEnrollmentRequest
import com.example.campusrootsinternapp.enrollment.data.model.InitiateEnrollmentRequest

class EnrollmentViewModel(private val repository: EnrollmentRepository) : ViewModel() {
    fun initiateEnrollment(request: InitiateEnrollmentRequest) {
        repository.initiateEnrollment(request)
    }

    fun completeEnrollment(request: CompleteEnrollmentRequest) {
        repository.completeEnrollment(request)
    }
}

class EnrollmentViewModelFactory(private val repository: EnrollmentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return EnrollmentViewModel(repository) as T
    }
}