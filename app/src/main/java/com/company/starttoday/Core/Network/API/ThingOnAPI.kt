package com.company.starttoday.Core.Network.API

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ThingOnAPI {
//    @GET("b/65e2bb3cdc74654018ac7f90?meta=false")
//    suspend fun getTweets(@Header("X-JSON-Path") category: String) : Response<List<stringEntity>>

    @GET("b/65e2bb3cdc74654018ac7f90?meta=false")
    @Headers("X-JSON-Path: HiltStudy..text")
    suspend fun getCategories(): Response<List<String>>


}