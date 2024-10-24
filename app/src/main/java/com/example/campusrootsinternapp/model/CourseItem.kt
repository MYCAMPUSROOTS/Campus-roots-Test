package com.example.campusrootsinternapp.model

data class CourseItem(
    val courseName: String,
    val courseTitle: String,
    val courseLecturer: String,
    val courseChannels: Int,
    val isActive: Boolean
)

val courseItems = listOf<CourseItem>(
    CourseItem("Course 1", "Title 1", "Lecturer 1", 10, true)
)