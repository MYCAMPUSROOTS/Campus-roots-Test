package com.example.campusrootsinternapp

import com.example.campusrootsinternapp.enrollment.model.CompleteEnrollmentRequest
import com.example.campusrootsinternapp.enrollment.model.CompleteEnrollmentResponse
import com.example.campusrootsinternapp.enrollment.model.GenericResponse
import com.example.campusrootsinternapp.enrollment.model.InitiateEnrollmentRequest
import com.example.campusrootsinternapp.model.PostResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
//    @GET("posts/1")
//    fun getPost(): Deferred<Response<PostResponse>>
    @GET("posts")
    fun getAllPosts(): Deferred<Response<List<PostResponse>>>
    @GET("posts/{var}")
    fun getPost(@Path("var") id : Int): Deferred<Response<PostResponse>>
    @POST("user/register")
    fun registerUser(request: InitiateEnrollmentRequest): Deferred<Response<GenericResponse>>
    @POST("user/login")
    fun completeEnrollment(enrollmentRequest: CompleteEnrollmentRequest): Deferred<Response<CompleteEnrollmentResponse>>
}