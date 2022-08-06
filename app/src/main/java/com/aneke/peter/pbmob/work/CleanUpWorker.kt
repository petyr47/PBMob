package com.aneke.peter.pbmob.work

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.aneke.peter.pbmob.data.AppDatabase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class CleanUpWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    val db : AppDatabase) : CoroutineWorker(appContext, workerParams) {

    companion object {
        const val TAG = "cleanup_worker"
    }


    override suspend fun doWork(): Result {
       try {
           val size = db.hitDao().fetchHits().size
           if (size >= 1000){
               db.hitDao().clearHits()
           }
           return Result.success()
       } catch (e : Exception){
           e.printStackTrace()
           return Result.retry()
       }
    }


}