package com.inspirecoding.workmanagerperiodic.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import com.inspirecoding.workmanagerperiodic.repository.SharedPreferencesRepository
import com.inspirecoding.workmanagerperiodic.workers.LOG_WORKER_TAG
import com.inspirecoding.workmanagerperiodic.workers.LogWorker
import java.util.concurrent.TimeUnit

class PeriodicWorkViewModel(app: Application) : AndroidViewModel(app)
{
    private val workManager = WorkManager.getInstance(app)
    private var sharedPreferencesRepository = SharedPreferencesRepository(app)

    fun startPeriodicWork()
    {
        val periodicWorkRequest : PeriodicWorkRequest = PeriodicWorkRequestBuilder<LogWorker>(
            15, TimeUnit.MINUTES)
            .addTag(LOG_WORKER_TAG)
            .build()
        workManager.enqueueUniquePeriodicWork(
            "MyUniqueWorkName",
            ExistingPeriodicWorkPolicy.REPLACE,
            periodicWorkRequest
        )
    }

    fun stopPeriodicWork()
    {
        workManager.cancelAllWorkByTag(LOG_WORKER_TAG)
    }

    fun getInfo() : String?
    {
        return sharedPreferencesRepository.getInfo()
    }

    fun clearInfo()
    {
        sharedPreferencesRepository.clearInfo()
    }
}