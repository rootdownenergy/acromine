package com.rootdown.dev.adidev_albertson.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.rootdown.dev.adidev_albertson.data.net.ApiService
import com.rootdown.dev.adidev_albertson.data.net.AuthApiService
import com.rootdown.dev.adidev_albertson.di.util.UtilSingleton
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    fun provideGson(): Gson = GsonBuilder().create()



    @Singleton
    @Provides
    @Named("api")
    fun provideRetrofitApi(gson: Gson, @ApplicationContext appContext: Context): Retrofit  = Retrofit.Builder()
        .baseUrl(UtilSingleton.api)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Singleton
    @Provides
    @Named("authapi")
    fun provideRetrofitAuth(gson: Gson, @ApplicationContext appContext: Context): Retrofit  = Retrofit.Builder()
        .baseUrl(UtilSingleton.auth)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    @Singleton
    fun provideAuthApiService(@Named("authapi") retrofit: Retrofit): AuthApiService = retrofit.create(AuthApiService::class.java)

    @Provides
    @Singleton
    fun provideApiService(@Named("api") retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}