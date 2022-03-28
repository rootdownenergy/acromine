package com.rootdown.dev.adidev_albertson.data.net

import com.rootdown.dev.adidev_albertson.data.model.AcromineResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AcromineService {
    @GET("sf")
    fun loadAcromine(@Query("sf") query: String): Response<AcromineResponse>
}