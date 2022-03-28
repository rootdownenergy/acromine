package com.rootdown.dev.adidev_albertson.di

import com.rootdown.dev.adidev_albertson.data.net.AcromineService
import com.rootdown.dev.adidev_albertson.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val baseURL = "http://www.nactem.ac.uk/software/acromine/dictionary.py"

    @Provides
    @Singleton
    fun getRemoteRepository(apiService: AcromineService): RemoteDataSource {
        return RemoteDataSource(apiService)
    }

    @Provides
    @Singleton
    fun getRetroServiceInterface(retrofit: Retrofit): AcromineService {
        return retrofit.create(AcromineService::class.java)
    }


    @Provides
    @Singleton
    fun getRetroFitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}