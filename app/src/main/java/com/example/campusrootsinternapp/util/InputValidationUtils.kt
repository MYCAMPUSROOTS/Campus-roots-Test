package com.example.campusrootsinternapp.util;

import android.net.http.HttpException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


object InputValidationUtils {

    fun handleException(exception: Throwable):String {
        println(exception)
        return  when (exception) {
            is SocketTimeoutException -> "Request time out. Try again"
            is UnknownHostException -> "Check your internet connection"
//            is HttpException -> "404 Not Found"
            is ConnectException -> "Check your internet connection"
//            is retrofit2.HttpException -> "reload"
            else -> "Something went wrong, try again"
        }
    }
}
fun Exception.handleException():String{
    return InputValidationUtils.handleException(this)
}
fun Throwable.handleException():String{
    return InputValidationUtils.handleException(this)
}