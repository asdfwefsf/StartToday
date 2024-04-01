package com.company.starttoday

import android.Manifest
import android.animation.ObjectAnimator
import android.app.AlarmManager
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.core.animation.doOnEnd
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.company.starttoday.Core.WorkManager.scheduleFetchImageLinkWork
import com.company.starttoday.Core.WorkManager.scheduleImmediateWork
import com.company.starttoday.Data.ThingOnData.Impl.UpdateThingOnRepositoryImpl
import com.company.starttoday.Data.ThingOnData.Room.ThingOnDatabase
import com.company.starttoday.Presentation.Screen.BottomNav
import com.company.starttoday.Theme.StartTodayTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class splashScreen : ViewModel() {
    private val _isReady = MutableStateFlow(false)
    val isReady = _isReady.asStateFlow()
    init {
        viewModelScope.launch {
            delay(10L)
            _isReady.value = true
        }
    }
}


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel by viewModels<splashScreen>()
    private lateinit var alarmManager: AlarmManager

    @Inject lateinit var thingonDB : ThingOnDatabase
    @Inject lateinit var repository: UpdateThingOnRepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
//        installSplashScreen()
        super.onCreate(savedInstanceState)
        alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        checkFirstRun(context = this)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !splashViewModel.isReady.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.1f,
                    2f,
                    1.0f
                )
                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 1500L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.1f,
                    2f,
                    1.0f
                )
                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 1500L
                zoomY.doOnEnd { screen.remove() }



                zoomX.start()
                zoomY.start()
            }
        }
        scheduleFetchImageLinkWork(this)
        setContent {
            StartTodayTheme {

                Surface(
                    color = MaterialTheme.colorScheme.primary
                ) {

                    BottomNav()

                    askNotificationPermission()
                    askPermissionForExactAlarm(this)
                }



            }
        }
    }
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            val allPermissionsGranted = permissions.all { it.value }
            if (allPermissionsGranted) {
                // 모든 권한이 허용되었을 때의 동작
            } else {
                // 권한 중 하나라도 허용되지 않았을 때의 동작

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    showPermissionRationalDialog()
                }
            }
        }


    fun showPermissionRationalDialog() {
        AlertDialog.Builder(this)
            .setMessage("알림 권한이 없으면 알림을 받을 수 없습니다.")
            .setPositiveButton("권한 허용하기") { _, _ ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    requestPermissionLauncher.launch(
                        arrayOf(
                            Manifest.permission.POST_NOTIFICATIONS,
                        )
                    )
                }

            }.setNegativeButton("취소") { dialogInterface, _ -> dialogInterface.cancel() }
            .show()
    }


    private fun askNotificationPermission() {
        // This is only necessary for API level >= 33 (TIRAMISU)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permissions = arrayOf(
                Manifest.permission.POST_NOTIFICATIONS,
                Manifest.permission.SCHEDULE_EXACT_ALARM
            )

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS,
                ) ==
                PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.SCHEDULE_EXACT_ALARM,
                ) ==
                PackageManager.PERMISSION_GRANTED
            ) {
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) ||
                shouldShowRequestPermissionRationale(Manifest.permission.SCHEDULE_EXACT_ALARM)
            ) {
                showPermissionRationalDialog()
            } else {
                requestPermissionLauncher.launch(permissions)
            }
        }
    }


    fun askPermissionForExactAlarm(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            if (!alarmManager.canScheduleExactAlarms()) {
                // 권한이 없으면 사용자에게 권한 요청
                val intent = Intent(AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED)
                context.startActivity(intent)
            } else {
                // 권한이 있으면 다음 단계로 (여기서는 예시로 비워둠)
            }
        }
    }

    // 앱 최초 다운 최초 실행
    fun checkFirstRun(context: Context): Boolean {
        val prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val isFirstRun = prefs.getBoolean("isFirstRun", true)

        if (isFirstRun) {
            scheduleImmediateWork(context)
            prefs.edit().putBoolean("isFirstRun", false).apply()
        }

        return isFirstRun
    }

}
