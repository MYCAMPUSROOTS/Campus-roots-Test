package com.example.campusrootsinternapp.repository

import com.example.campusrootsinternapp.ApiService
import com.example.campusrootsinternapp.model.PostResponse
import com.example.campusrootsinternapp.util.UseCaseResult
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject

interface AppRepository {
    suspend fun getPosts(): UseCaseResult<Response<PostResponse>>
    suspend fun getAllPosts(): UseCaseResult<Response<List<PostResponse>>>

}

class AppRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : AppRepository {
    override suspend fun getPosts(): UseCaseResult<Response<PostResponse>> {
        return try {
            val response = apiService.getPost().await()
            if(response.isSuccessful){

                UseCaseResult.Success(response)
            }else{
                UseCaseResult.FailedAPI(response)
            }
        }catch (ex: Exception){
            Timber.e(ex)
            UseCaseResult.Error(ex)
        }
    }

    override suspend fun getAllPosts(): UseCaseResult<Response<List<PostResponse>>> {
        return try {
            val response = apiService.getAllPosts().await()
            if(response.isSuccessful){
                UseCaseResult.Success(response)
            }else{
                UseCaseResult.FailedAPI(response)
            }
        }catch (ex: Exception){
            Timber.e(ex)
            UseCaseResult.Error(ex)
        }
    }

}