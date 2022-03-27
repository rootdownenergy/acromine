package com.rootdown.dev.adidev_albertson.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    private val baseURL = "http://www.nactem.ac.uk/software/acromine/dictionary.py"


}