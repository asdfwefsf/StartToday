package com.company.starttoday.Core.Network.API

import com.company.starttoday.Data.ForecastData.forecastEntity
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("/1360000/VilageFcstInfoService_2.0/getVilageFcst?pageNo=1&numOfRows=400&dataType=json")
    fun getVillageForecast(
        @Query("serviceKey") serviceKey : String,
        @Query("base_date") baseDate : String,
        @Query("base_time") baseTime : String,
        @Query("nx") nx : Int,
        @Query("ny") ny : Int
    ) : Call<forecastEntity>


}