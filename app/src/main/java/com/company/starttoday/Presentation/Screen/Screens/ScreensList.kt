package com.company.starttoday.Presentation.Screen.Screens

sealed class Screen (val route : String) {
    object MainScreen : Screen("홈")
    object AlarmScreen : Screen("알람")
    object RoutineScreen : Screen("일정")
    object setAlarmScreen : Screen("setAlarm")
}


