package com.example.campusrootsinternapp.util;


sealed class UseCaseResult<out T : Any> {
    class Success<out T : Any>(val data: T) : UseCaseResult<T>()
    class Error(val exception: Throwable) : UseCaseResult<Nothing>()
    class ErrorAPI<out T : Any>(val data: T?, val exception: Throwable): UseCaseResult<T>()
    class SessionTimeOut(val errorMessage:String): UseCaseResult<Nothing>()
    class FailedAPI<out T : Any>(val data: T): UseCaseResult<T>()
}