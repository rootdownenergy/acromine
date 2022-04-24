package com.rootdown.dev.adidev_albertson.data.repo

import android.util.Log
import androidx.lifecycle.LiveData
import com.rootdown.dev.adidev_albertson.data.local.AcrominDataItem
import com.rootdown.dev.adidev_albertson.data.local.AcromineDao
import com.rootdown.dev.adidev_albertson.data.local.AppDatabase
import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.data.model.remote.AcromineSearchResult
import com.rootdown.dev.adidev_albertson.data.net.ApiService
import com.rootdown.dev.adidev_albertson.di.util.ApplicationScope
import com.rootdown.dev.adidev_albertson.di.util.DefaultDispatcher
import com.rootdown.dev.adidev_albertson.di.util.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepoImpl @Inject constructor(
    private val api: ApiService,
    private val acromineDao: AcromineDao,
    @IoDispatcher private val defaultIoDispatcher: CoroutineDispatcher
) : SearchRepo {
    override suspend fun getAcro(q: String): AcromineFull.AcromineFullItem {
        lateinit var result: AcromineFull.AcromineFullItem
        withContext(defaultIoDispatcher){
            val acroLs = api.getAcromine(q)
            if(acroLs.isEmpty()){
                result = AcromineFull.AcromineFullItem(
                    lfs = listOf(AcromineFull.AcromineFullItem.Lf(
                        lf = "ERROR",
                        freq = 1,
                        since = 1985,
                        vars = listOf(AcromineFull.AcromineFullItem.Lf.Var(freq = 1, lf = "", since = 1985))
                    )), sf = "")
            } else {
                result = acroLs[0]
            }
        }
        if(result.equals(null))
        {
            result = AcromineFull.AcromineFullItem(
                lfs = listOf(AcromineFull.AcromineFullItem.Lf(
                    lf = "ERROR",
                    freq = 1,
                    since = 1985,
                    vars = listOf(AcromineFull.AcromineFullItem.Lf.Var(freq = 1, lf = "", since = 1985))
                )), sf = "")
        }
        return result
    }

    override suspend fun saveSearch(xIn: AcrominDataItem) {
        acromineDao.insertAll(xIn)
    }

    override suspend fun getSearches(): Flow<List<AcrominDataItem>> {
        return acromineDao.getAcromine()
    }

    override suspend fun deleteAcromineItem(id: Int) {
        acromineDao.deleteAcromineItem(id)
    }
}