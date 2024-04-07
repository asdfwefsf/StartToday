package com.company.starttoday.Core.Network.API

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface ImageLinkAPI {

    @GET("b/65e2bb3cdc74654018ac7f90?meta=false")
    @Headers("X-JSON-Path: HiltStudy..image")
    suspend fun getImages(): Response<List<String>>



}