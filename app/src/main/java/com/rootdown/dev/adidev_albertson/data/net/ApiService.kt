package com.rootdown.dev.adidev_albertson.data.net


import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.data.model.remote.AcromineSearchResult
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response

interface ApiService {
    @GET("dictionary.py?")
    suspend fun getAcromine(
        @Query("sf") query: String
    ) : AcromineFull
    @GET("dictionary.py?")
    suspend fun getAcromineSearchResult(
        @Query("sf") query: String
    ) : Response<AcromineSearchResult>

    @GET("dictionary.py?")
    fun searchByPhrase(@Query("sf") searchPhrase: String): Call<ResponseBody>

}