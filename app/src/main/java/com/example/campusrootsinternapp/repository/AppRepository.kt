package com.example.campusrootsinternapp.repository

import com.example.campusrootsinternapp.ApiService
import com.example.campusrootsinternapp.enrollment.model.CompleteEnrollmentRequest
import com.example.campusrootsinternapp.enrollment.model.CompleteEnrollmentResponse
import com.example.campusrootsinternapp.enrollment.model.GenericResponse
import com.example.campusrootsinternapp.enrollment.model.InitiateEnrollmentRequest
import com.example.campusrootsinternapp.model.PostResponse
import com.example.campusrootsinternapp.util.UseCaseResult
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

interface AppRepository {
    suspend fun getPost(postId: Int): UseCaseResult<Response<PostResponse>>
    suspend fun getAllPosts(): UseCaseResult<Response<List<PostResponse>>>
    suspend fun completeEnrollment(enrollmentRequest: CompleteEnrollmentRequest): UseCaseResult<Response<CompleteEnrollmentResponse>>
    suspend fun registerUser(request: InitiateEnrollmentRequest): UseCaseResult<Response<GenericResponse>>
}

class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AppRepository {

    override suspend fun getPost(postId: Int): UseCaseResult<Response<PostResponse>> = try {
        apiService.getPost(postId).await().let { response ->
            if (response.isSuccessful) UseCaseResult.Success(response)
            else UseCaseResult.FailedAPI(response)
        }
    } catch (ex: Exception) {
        Timber.e(ex)
        UseCaseResult.Error(ex)
    }

    override suspend fun getAllPosts(): UseCaseResult<Response<List<PostResponse>>> = try {
        apiService.getAllPosts().await().let { response ->
            if (response.isSuccessful) UseCaseResult.Success(response)
            else UseCaseResult.FailedAPI(response)
        }
    } catch (ex: Exception) {
        Timber.e(ex)
        UseCaseResult.Error(ex)
    }

    override suspend fun registerUser(request: InitiateEnrollmentRequest): UseCaseResult<Response<GenericResponse>> = try {
        apiService.registerUser(request).await().let { response ->
            if (response.isSuccessful) UseCaseResult.Success(response)
            else UseCaseResult.FailedAPI(response)
        }
    } catch (ex: Exception) {
        Timber.e(ex)
        UseCaseResult.Error(ex)
    }

    override suspend fun completeEnrollment(enrollmentRequest: CompleteEnrollmentRequest): UseCaseResult<Response<CompleteEnrollmentResponse>> = try {
        apiService.completeEnrollment(enrollmentRequest).await().let { response ->
            if (response.isSuccessful) UseCaseResult.Success(response)
            else UseCaseResult.FailedAPI(response)
        }
    } catch (ex: Exception) {
        Timber.e(ex)
        UseCaseResult.Error(ex)
    }
}