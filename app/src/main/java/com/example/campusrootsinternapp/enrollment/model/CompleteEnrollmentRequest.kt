package com.example.campusrootsinternapp.enrollment.model

data class CompleteEnrollmentRequest(
    val email:String,
    val first_name:String,
    val last_name:String,
    val otp:String,
    val ambassador: String?,
    val username: String,
    val password:String
)