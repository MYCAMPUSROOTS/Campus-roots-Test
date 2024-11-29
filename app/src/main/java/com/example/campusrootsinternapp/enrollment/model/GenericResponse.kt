package com.example.campusrootsinternapp.enrollment.model

import java.util.stream.Stream

data class GenericResponse(
    val status: String,
    val response_code: Int,
    val response_message: String,
    val task: Stream<Any>?,
//    val user: User?,
    val url: String?,
)