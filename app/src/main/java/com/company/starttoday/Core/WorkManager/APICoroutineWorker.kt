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
import com.company.starttoday.Domain.Image.UseCases.GetImageUseCase
import com.company.starttoday.Domain.ThingOn.UseCases.GetThingOnUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.coroutineScope
import java.util.Calendar
import java.util.concurrent.TimeUnit



@HiltWorker
class APICoroutineWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val getImageUseCase: GetImageUseCase,
    private val getStringUseCase: GetThingOnUseCase

) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = coroutineScope {
        try {
            getImageUseCase.getImage()
            getStringUseCase.getString()
            Log.d("karina" , "karinaTt")
        } catch (e: Exception) {
            Result.failure()
        }
        Result.success()
    }
}

fun getInfoWork(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()
    // 작업 요청에 대한 제한 조건 설정 : 여기서는 네트워크가 연결되어야 한다는 조건

    val currentDate = Calendar.getInstance()

    val dueDate = Calendar.getInstance().apply {
        // 아래에서 서버에서 데이터 가져오는 날짜 , 시간 설정
        set(Calendar.HOUR_OF_DAY, 3)
        set(Calendar.MINUTE, 45
        )
        set(Calendar.SECOND, 0)
        if (before(currentDate)) { // 하루 지나면 오늘 + 1
            add(Calendar.DATE, 1)
        }
    }

    // 바로 실행 말고 현재 시간과 예약 시간의 차이가 흐로고 작업이 실행
    val timeDiff = dueDate.timeInMillis - currentDate.timeInMillis

    // 한번만 사용하는 WorkRequest 생성
    val dailyWorkRequest = OneTimeWorkRequestBuilder<APICoroutineWorker>()
        .setInitialDelay(timeDiff, TimeUnit.MILLISECONDS)
        .setConstraints(constraints)
        .build()

    // WorkManager에 작업 요청을 예약
    WorkManager.getInstance(context).enqueue(dailyWorkRequest)
}


fun getInfoNowWork(context: Context) {
    val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.CONNECTED)
        .build()

    // 빨리 해야되서 지연시간 X
    val immediateWorkRequest = OneTimeWorkRequestBuilder<APICoroutineWorker>()
        .setConstraints(constraints)
        .build()

    Log.d("first" , "dd")
    WorkManager.getInstance(context).enqueue(immediateWorkRequest)

}