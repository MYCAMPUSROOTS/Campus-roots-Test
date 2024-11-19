package com.example.campusrootsinternapp.di

import android.content.Context
import com.example.campusrootsinternapp.ApiService
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkModule {
    val BASE_URL = "https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun provideHttpLogger(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(logger: HttpLoggingInterceptor, provideApplicationContext: Context): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor((logger))
            .readTimeout(10, TimeUnit.MINUTES)
            .connectTimeout(  5, TimeUnit.MINUTES)
            .writeTimeout(5, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    fun  provideRetrofit( okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().
        baseUrl(BASE_URL).
        client(okHttpClient).
        addCallAdapterFactory(CoroutineCallAdapterFactory()).
        addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create())).build()

    @Provides
    @Singleton
    fun provideAPIService(provideRetrofit: Retrofit): ApiService = provideRetrofit.create(ApiService::class.java)
}