package com.inspirecoding.workmanagerperiodic.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.inspirecoding.workmanagerperiodic.repository.SharedPreferencesRepository
import java.util.*

// 1
const val LOG_WORKER_TAG = "LogWorker"

// 2
class LogWorker (val context: Context, params: WorkerParameters) : Worker(context, params)
{
    // 3
    private var sharedPreferencesRepository: SharedPreferencesRepository = SharedPreferencesRepository(context)

    // 4
    override fun doWork(): Result
    {
        // 5
        try
        {
            // 6
            var info = sharedPreferencesRepository.getInfo()

            // 7
            if (info != null)
            {
                info += "\n${Calendar.getInstance().time.toLocaleString()}"
                sharedPreferencesRepository.setInfo(info)
            }
            else
            {
                info = Calendar.getInstance().time.toLocaleString()
                sharedPreferencesRepository.setInfo(info)
            }

            // 8
            return Result.success()
        }
        catch (exception: Exception)
        {
            return Result.retry()
        }
    }
}