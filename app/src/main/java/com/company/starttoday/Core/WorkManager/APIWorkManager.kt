package com.company.starttoday.Core.WorkManager

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.company.starttoday.Data.Impl.ImageLinkImpl
import com.company.starttoday.Data.Impl.StringRepositoryImpl
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope
import java.util.Calendar
import java.util.concurrent.TimeUnit

@HiltWorker
class APIWorkManager @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val imageLinkImpl : ImageLinkImpl,
    private val stringRepositoryImpl : StringRepositoryImpl
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            imageLinkImpl.getImageLink()
            stringRepositoryImpl.getCategories()
            Log.d("karina" , "karinaT")
//            return Result.success()
        } catch (e: Exception) {
            Log.d("karina" , "karinaF")

//            return Result.retry()
        }
        Result.success()
    }
}

fun scheduleFetchImageLinkWork(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val currentDate = Calendar.getInstance()

    val dueDate = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 8)
        set(Calendar.MINUTE, 0
        )
        set(Calendar.SECOND, 0)
        if (before(currentDate)) {
            add(Calendar.DATE, 1)
        }
    }

    val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis
    val dailyWorkRequest = OneTimeWorkRequestBuilder<APIWorkManager>()
        .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
        .setConstraints(constraints)
        .build()

    WorkManager.getInstance(context).enqueue(dailyWorkRequest)
}

fun scheduleImmediateWork(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    val immediateWorkRequest = OneTimeWorkRequestBuilder<APIWorkManager>()
        .setConstraints(constraints)
        .build()

    Log.d("first" , "dd")
    WorkManager.getInstance(context).enqueue(immediateWorkRequest)

}