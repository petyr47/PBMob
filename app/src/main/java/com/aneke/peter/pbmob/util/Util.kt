package com.aneke.peter.pbmob.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.aneke.peter.pbmob.R
import com.aneke.peter.pbmob.work.CleanUpWorker
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class Util @Inject constructor(val context: Context) {

    fun provideKey() = context.getString(R.string.api_key)

    fun isConnected(): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            ?: return false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val cap = cm.getNetworkCapabilities(cm.activeNetwork) ?: return false
            return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            val networks = cm.allNetworks
            for (n in networks) {
                val nInfo = cm.getNetworkInfo(n)
                if (nInfo != null && nInfo.isConnected) return true
            }
            return false
        }
    }

    fun enqueuePeriodicWork() {
        val constraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresDeviceIdle(true)
            .build()

        val periodicWork = PeriodicWorkRequestBuilder<CleanUpWorker>(2, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(CleanUpWorker.TAG, ExistingPeriodicWorkPolicy.REPLACE, periodicWork)

    }
}

fun resolveTags(tags : String) : List<String> = tags.split(",").map { it.trim().capitalize(Locale.getDefault()) }