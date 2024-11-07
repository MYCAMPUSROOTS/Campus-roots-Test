package com.example.campusrootsinternapp

import com.example.campusrootsinternapp.model.PostResponse
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("posts/1")
    fun getPost(): Deferred<Response<PostResponse>>
}