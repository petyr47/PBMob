package com.aneke.peter.pbmob.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Hit::class], exportSchema = false, version = 2)
abstract class AppDatabase : RoomDatabase() {

    abstract fun hitDao() : HitDao

    companion object {
        private const val databaseName = "app_db"

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                databaseName)
                .fallbackToDestructiveMigration()
                .build()
        }
    }



}