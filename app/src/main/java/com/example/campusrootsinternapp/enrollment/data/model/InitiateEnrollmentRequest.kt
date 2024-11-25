package com.example.campusrootsinternapp.enrollment.data.model

data class InitiateEnrollmentRequest(
    val email:String,
    val first_name:String,
    val username: String,
    val last_name:String
)