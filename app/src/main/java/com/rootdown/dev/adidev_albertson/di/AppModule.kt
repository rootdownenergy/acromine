package com.rootdown.dev.adidev_albertson.di

import com.rootdown.dev.adidev_albertson.data.local.AcromineDao
import com.rootdown.dev.adidev_albertson.data.net.ApiService
import com.rootdown.dev.adidev_albertson.data.repo.SearchRepo
import com.rootdown.dev.adidev_albertson.data.repo.SearchRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSearchRepoImpl(
        service: ApiService,
        dao: AcromineDao
    ) = SearchRepoImpl(service,dao) as SearchRepo
}