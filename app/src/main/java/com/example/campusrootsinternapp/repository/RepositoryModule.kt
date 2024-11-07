package com.example.campusrootsinternapp.repository

import com.example.campusrootsinternapp.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideDeliveryRepository(apiService : ApiService): AppRepository = AppRepositoryImpl(
        apiService
    )

}