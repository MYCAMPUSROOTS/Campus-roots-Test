package com.example.campusrootsinternapp.model

data class CourseItem(
    val courseName: String,
    val courseTitle: String,
    val courseLecturer: String,
    val courseChannels: Int,
    val isActive: Boolean
)

//val courseItems = listOf<CourseItem>(
////    CourseItem("Course 1", "Title 1", "Lecturer 1", 10, true)
//    CourseItem("MTH 101", "Student Elementary Mathematics TA", "Markus Callaway", 7, true),
//    CourseItem("PHY 211", "Principles of Quantum Physics", "Markus Callaway", 7, true),
//    CourseItem("BUS 103", "Introduction to Business", "Markus Callaway", 7, false),
//    CourseItem("MTH 101", "Student Elementary Mathematics TA", "Markus Callaway", 7, false),
//    CourseItem("PHY 211", "Principles of Quantum Physics", "Markus Callaway", 7, false),
//    CourseItem("BUS 103", "Introduction to Business", "Markus Callaway", 7, false)
//)