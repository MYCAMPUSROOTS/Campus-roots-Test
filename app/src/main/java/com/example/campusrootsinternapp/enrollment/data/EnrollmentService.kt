package com.example.campusrootsinternapp.enrollment.data

import com.example.campusrootsinternapp.enrollment.data.model.CompleteEnrollmentRequest
import com.example.campusrootsinternapp.enrollment.data.model.CompleteEnrollmentResponse
import com.example.campusrootsinternapp.enrollment.data.model.GenericResponse
import com.example.campusrootsinternapp.enrollment.data.model.InitiateEnrollmentRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface EnrollmentService {
    @POST("user/register")
    fun initiateEnrollment(@Body request: InitiateEnrollmentRequest): GenericResponse

    @POST("user/submit-otp")
    fun completeEnrollment(@Body request: CompleteEnrollmentRequest): CompleteEnrollmentResponse
}