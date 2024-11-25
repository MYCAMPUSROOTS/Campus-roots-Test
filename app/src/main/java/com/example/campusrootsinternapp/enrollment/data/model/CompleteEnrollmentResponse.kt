package com.example.campusrootsinternapp.enrollment.data.model

data class CompleteEnrollmentResponse(
    val response_code: Int,
    val response_message: String,
    val user: User,
    val token: String
)