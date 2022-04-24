package com.rootdown.dev.adidev_albertson.di

import com.rootdown.dev.adidev_albertson.data.local.AcromineDao
import com.rootdown.dev.adidev_albertson.data.net.ApiService
import com.rootdown.dev.adidev_albertson.data.repo.SearchRepo
import com.rootdown.dev.adidev_albertson.data.repo.SearchRepoImpl
import com.rootdown.dev.adidev_albertson.di.util.DefaultDispatcher
import com.rootdown.dev.adidev_albertson.di.util.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton



@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelCoroutineIOModule {
    @Provides
    @ViewModelScoped
    fun providesCoroutineScopeIO(
        @IoDispatcher defaultDispatcher: CoroutineDispatcher
    ): CoroutineDispatcher = defaultDispatcher
}



@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSearchRepoImpl(
        service: ApiService,
        dao: AcromineDao,
        @IoDispatcher defaultDispatcher: CoroutineDispatcher
    ) = SearchRepoImpl(service,dao,defaultDispatcher) as SearchRepo
}