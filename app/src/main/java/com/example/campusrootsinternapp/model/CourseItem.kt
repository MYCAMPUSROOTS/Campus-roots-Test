package com.example.campusrootsinternapp.model

import java.io.Serializable

data class CourseItem(
    val courseName: String,
    val courseTitle: String,
    val courseLecturer: String,
    val courseChannels: Int,
    val isActive: Boolean
): Serializable