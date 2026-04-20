package com.example.quietworkouts

import android.content.Context
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit
import kotlin.random.Random

object WorkoutScheduler {

    const val UNIQUE_WORK_NAME = "quiet_workouts_chain"

    private const val MIN_MINUTES = 60L
    private const val MAX_MINUTES = 180L

    fun scheduleNext(context: Context) {
        val delayMinutes = Random.nextLong(MIN_MINUTES, MAX_MINUTES + 1)

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .build()

        val request = OneTimeWorkRequestBuilder<WorkoutWorker>()
            .setInitialDelay(delayMinutes, TimeUnit.MINUTES)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(context).enqueueUniqueWork(
            UNIQUE_WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )

        val scheduledAt = System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(delayMinutes)
        context.getSharedPreferences("quiet_workouts", Context.MODE_PRIVATE)
            .edit()
            .putLong("next_trigger_at", scheduledAt)
            .putBoolean("enabled", true)
            .apply()
    }

    fun cancel(context: Context) {
        WorkManager.getInstance(context).cancelUniqueWork(UNIQUE_WORK_NAME)
        context.getSharedPreferences("quiet_workouts", Context.MODE_PRIVATE)
            .edit()
            .putBoolean("enabled", false)
            .remove("next_trigger_at")
            .apply()
    }

    fun isEnabled(context: Context): Boolean =
        context.getSharedPreferences("quiet_workouts", Context.MODE_PRIVATE)
            .getBoolean("enabled", false)

    fun nextTriggerAt(context: Context): Long =
        context.getSharedPreferences("quiet_workouts", Context.MODE_PRIVATE)
            .getLong("next_trigger_at", 0L)
}
