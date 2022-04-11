package com.rootdown.dev.adidev_albertson.data.net

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthApiService {
    @POST("/api/register")
    suspend fun registerUser(
        @Query("name") name: String,
        @Query("email") email: String,
        @Query("password") password: String,
        @Query("c_password") c_password: String,
    ) : Response<String>
    companion object {
        const val BASE_URL = "https://dev.rootdownsoil.com/"
        fun ini(): AuthApiService {
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
                .create(AuthApiService::class.java)
        }
    }
}