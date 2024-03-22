package com.company.starttoday.Data.Impl

import android.content.Context
import android.util.Log
import com.company.starttoday.Core.Network.API.WeatherAPI
import com.company.starttoday.Data.ForecastData.forecastEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class forecastRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherAPI,
    private val context: Context
) {

    data class rainEntity(
        val possible : Int
    )

    private val _rainPossible = MutableStateFlow<List<Int>>(emptyList())

    val rainPossible : StateFlow<List<Int>>
        get() = _rainPossible


    suspend fun getVillageForecast(nx : Int , ny : Int , settingHour : String) {
        CoroutineScope(Dispatchers.IO).launch {
            val formatter = DateTimeFormatter.ofPattern("HH:mm")
            val dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd")

            var currentTime = LocalTime.now().toString()
            var currentTimeHourMinute = currentTime.format(formatter).split(":")

            var currentHour = currentTimeHourMinute[0]
            var currentDate = LocalDateTime.now().format(dateFormatter)

            var baseTime = ""
            // 0200 0500 0800 1100 1400 1700 2000 2300
            if (currentHour.toInt() in 2..4) {
                baseTime = "0200"
            } else if (currentHour.toInt() in 5..7) {
                baseTime = "0500"
            } else if (currentHour.toInt() in 8..10) {
                baseTime = "0800"
            } else if (currentHour.toInt() in 11..13) {
                baseTime = "1100"
            } else if (currentHour.toInt() in 14..16) {
                baseTime = "1400"
            } else if (currentHour.toInt() in 17..19) {
                baseTime = "1700"
            } else if (currentHour.toInt() in 20..23) {
                baseTime = "2000"
            } else if (currentHour.toInt() >= 23) {
                baseTime = "2300"
            } else if (currentHour.toInt() in 0..1) {
                baseTime = "2300"
            }

            weatherApi.getVillageForecast(
                serviceKey = "2ZjTr1OHCDPvBZ7zEPIm1fsUc44O+Q047igb8U0q8RXcKWJCeNMCbqSiUmTDnh+Yt/ot9Qml+A3ryXg8vfIidg==",
                baseDate = "${currentDate.toInt()}",
                baseTime = "${baseTime.toInt()}",
                nx = nx,
                ny = ny
            ).enqueue(object : Callback<forecastEntity> {
                override fun onResponse(
                    call: Call<forecastEntity>,
                    response: Response<forecastEntity>
                ) {
                    val forecastList =
                        response.body()?.response?.body?.items?.forecastList.orEmpty()


                    val rainProbabilities = forecastList.filter { item ->
                        item.category.equals("POP", ignoreCase = true)
                                && item.fcstTime >= settingHour
                    }.map {
                        it.fcstValue.toInt()
                    }

                    _rainPossible.value = rainProbabilities


                    for (forecast in forecastList) {
                        val popForecasts = forecastList.filter {
                            it.category.equals("POP", ignoreCase = true)
                        }
                        if (popForecasts[5].fcstValue.toInt() >= 0) {
                            Log.d("Forecast", popForecasts[0].toString())
                            Log.d("Forecast", popForecasts[1].toString())
                            Log.d("Forecast", popForecasts[2].toString())
                            Log.d("Forecast", popForecasts[3].toString())
                            Log.d("Forecast", popForecasts[4].toString())
                            Log.d("Forecast", popForecasts[5].toString())
                            Log.d("currentHour", currentHour)
                        }
                    }
                }

                override fun onFailure(call: Call<forecastEntity>, t: Throwable) {
                }

            })
        }
    }



}