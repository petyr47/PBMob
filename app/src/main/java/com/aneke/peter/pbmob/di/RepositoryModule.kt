package com.aneke.peter.pbmob.di

import com.aneke.peter.pbmob.data.AppDatabase
import com.aneke.peter.pbmob.network.ApiInterface
import com.aneke.peter.pbmob.repository.SearchRepository
import com.aneke.peter.pbmob.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideSearchRepository(key: Util, db : AppDatabase, api: ApiInterface) : SearchRepository = SearchRepository(key, db, api)


}