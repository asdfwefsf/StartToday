package com.company.starttoday.Data.ForecastData

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class forecastEntity(
    @SerializedName("response")
    val response : forecastResponse
)

data class forecastResponse(
    @SerializedName("header")
    val header : forecastHeader,
    @SerializedName("body")
    val body : forecastBody
)

data class forecastHeader(
    @SerializedName("resultCode")
    val resultCode : String,
    @SerializedName("resultMsg")
    val resultMsg : String
)

data class forecastBody(
    @SerializedName("items")
    val items : forecastLists
)

data class forecastLists(
    @SerializedName("item")
    val forecastList : List<forecastItems>
)

data class forecastItems(
    @SerializedName("baseDate")
    val baseDate : String,
    @SerializedName("baseTime")
    val baseTime : String,
    @SerializedName("category")
    val category : String,
    @SerializedName("fcstDate")
    val fcstDate : String,
    @SerializedName("fcstTime")
    val fcstTime : String,
    @SerializedName("fcstValue")
    val fcstValue : String,
    @SerializedName("nx")
    val nx : Int,
    @SerializedName("ny")
    val ny : Int
)