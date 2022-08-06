package com.aneke.peter.pbmob.di

import android.content.Context
import com.aneke.peter.pbmob.data.AppDatabase
import com.aneke.peter.pbmob.util.Util
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        AppDatabase.getInstance(context)


    @Provides
    @Singleton
    fun provideKeyHolder(@ApplicationContext context: Context) : Util = Util(context)


}