package com.rootdown.dev.adidev_albertson.data.repo

import androidx.lifecycle.LiveData
import com.rootdown.dev.adidev_albertson.data.local.AcrominDataItem
import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.data.model.remote.AcromineSearchResult
import com.rootdown.dev.adidev_albertson.util.Resource
import kotlinx.coroutines.flow.Flow

interface SearchRepo {
    suspend fun getAcro(q: String): AcromineFull.AcromineFullItem
    suspend fun saveSearch(xIn: AcrominDataItem)
    suspend fun getSearches(): Flow<List<AcrominDataItem>>
    suspend fun deleteAcromineItem(id: Int)
}