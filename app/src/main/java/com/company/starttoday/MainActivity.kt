package com.company.starttoday

import android.Manifest
import android.animation.ObjectAnimator
import android.app.AlarmManager
import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
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
import com.company.starttoday.Core.WorkManager.getInfoNowWork
import com.company.starttoday.Core.WorkManager.getInfoWork
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

class AlarmPermissionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == AlarmManager.ACTION_SCHEDULE_EXACT_ALARM_PERMISSION_STATE_CHANGED) {

Log.d("sibal" , intent.action.toString())
Log.d("sibal" , "sibal")
            val alarmManager = context?.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
            if (alarmManager != null && !alarmManager.canScheduleExactAlarms()) {
                // 사용자에게 권한 허용을 요청하는 다이얼로그를 표시
                showPermissionRequestDialog(context)
            }
        }
    }

    private fun showPermissionRequestDialog(context: Context) {
        // 사용자에게 권한 허용을 요청하는 다이얼로그 표시
        AlertDialog.Builder(context).apply {
            setTitle("권한 필요")
            setMessage("알람을 정확하게 설정하려면 권한이 필요합니다. 설정 페이지로 이동하시겠습니까?")
            setPositiveButton("네") { dialog, which ->
                // 사용자를 설정 페이지로 안내하는 인텐트 실행
                val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                context.startActivity(intent)
            }
            setNegativeButton("아니오", null)
            show()
        }
    }
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val splashViewModel by viewModels<splashScreen>()
    private lateinit var alarmManager: AlarmManager

    @Inject
    lateinit var thingonDB: ThingOnDatabase

    @Inject
    lateinit var repository: UpdateThingOnRepositoryImpl

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
        getInfoWork(this)
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

    fun askPermissionForExactAlarm(context: Context) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (!alarmManager.canScheduleExactAlarms()) {
            Log.d("sibal1" , "sibal1")
            Log.d("sibal1" , "sibal1")
            val intent = Intent(Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM)
            context.startActivity(intent)

        }
        Log.d("sibal2" , intent.action.toString())
        Log.d("sibal2" , "sibal")

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
            )

            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS,
                ) ==
                PackageManager.PERMISSION_GRANTED
//                &&
//                ContextCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.USE_EXACT_ALARM,
//                ) ==
//                PackageManager.PERMISSION_GRANTED
            ) {
            } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS) ||
                shouldShowRequestPermissionRationale(Manifest.permission.USE_EXACT_ALARM)
            ) {
                showPermissionRationalDialog()
            } else {
                requestPermissionLauncher.launch(permissions)
            }
        }
    }


    // 앱 최초 다운 최초 실행
    fun checkFirstRun(context: Context): Boolean {
        val prefs = context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
        val isFirstRun = prefs.getBoolean("isFirstRun", true)

        if (isFirstRun) {
            getInfoNowWork(context)
            prefs.edit().putBoolean("isFirstRun", false).apply()
        }

        return isFirstRun
    }


}
