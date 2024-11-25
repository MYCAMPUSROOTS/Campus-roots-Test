package com.example.campusrootsinternapp.enrollment.data

import com.example.campusrootsinternapp.enrollment.data.model.CompleteEnrollmentRequest
import com.example.campusrootsinternapp.enrollment.data.model.CompleteEnrollmentResponse
import com.example.campusrootsinternapp.enrollment.data.model.GenericResponse
import com.example.campusrootsinternapp.enrollment.data.model.InitiateEnrollmentRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EnrollmentRepository {
    private val api: EnrollmentService

    init {
        val NETWORK_BASE_URL = "https://staging.mycampusroots.com/api/"
        val retrofit = Retrofit.Builder()
            .baseUrl(NETWORK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(EnrollmentService::class.java)
    }

    fun initiateEnrollment(request: InitiateEnrollmentRequest): GenericResponse {
        return api.initiateEnrollment(request)
    }

    fun completeEnrollment(request: CompleteEnrollmentRequest): CompleteEnrollmentResponse {
        return api.completeEnrollment(request)
    }
}