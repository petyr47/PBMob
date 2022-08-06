package com.aneke.peter.pbmob.repository

import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.aneke.peter.pbmob.data.AppDatabase
import com.aneke.peter.pbmob.network.ApiInterface
import com.aneke.peter.pbmob.network.model.SearchImageResponse
import com.aneke.peter.pbmob.util.Util
import com.aneke.peter.pbmob.util.resolveMessage
import com.aneke.peter.pbmob.work.CleanUpWorker
import java.net.UnknownHostException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SearchRepository @Inject constructor(
    val util: Util,
    val db: AppDatabase,
    val api: ApiInterface
) {


    suspend fun search(query: String) =
        when {
            util.isConnected() -> queryPixabay(query)
            else -> queryOffline(query)
        }

    private suspend fun queryPixabay(query: String): SearchImageResponse {
        try {
            val result = api.fetchHits(key = util.provideKey(), query = query)
            val hits = result.hits.map { it.copy(searchQuery = query) }
            db.hitDao().insertHits(hits)
            util.enqueuePeriodicWork()
            return result
        } catch (e : Exception){
            e.printStackTrace()
            if (e is UnknownHostException) {
                return SearchImageResponse(message = e.resolveMessage(), success = false)
            }
            return queryOffline(query)
        }
    }

    private suspend fun queryOffline(query: String): SearchImageResponse {
        try {
           val hits = db.hitDao().searchHits(query)
            return if (hits.isEmpty()) SearchImageResponse(message = "No Results found", success = false) else SearchImageResponse(hits = hits)
        } catch (e : Exception) {
            e.printStackTrace()
            return SearchImageResponse(message = e.resolveMessage(), success = false)
        }
    }

}

