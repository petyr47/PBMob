package com.aneke.peter.pbmob.di

import com.aneke.peter.pbmob.network.ApiInterface
import com.aneke.peter.pbmob.network.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideClient(): ApiInterface = RetrofitClient.makeApiService()

}


