package com.company.starttoday.Presentation.Screens

sealed class Screen (val route : String) {
    object MainScreen : Screen("Main")
    object AlarmScreen : Screen("Alarm")
    object RoutineScreen : Screen("Routine")
    object setAlarmScreen : Screen("setAlarm")
    object WeatherScreen : Screen("Weather")
}


