package com.rootdown.dev.adidev_albertson.data.net


import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("dictionary.py?")
    suspend fun getAcromine(
        @Query("sf") query: String
    ) : AcromineFull
    companion object {
        const val BASE_URL = "http://www.nactem.ac.uk/software/acromine/"
        fun ini(): ApiService {
            val logger = HttpLoggingInterceptor()
            logger.level = HttpLoggingInterceptor.Level.BASIC

            val client = OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
        }
    }
}