package com.rootdown.dev.adidev_albertson.data.repo

import com.rootdown.dev.adidev_albertson.data.local.AcrominDataItem
import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.data.model.remote.AcromineSearchResult

interface SearchRepo {
    suspend fun getAcro(q: String): AcromineFull.AcromineFullItem
    suspend fun saveSearch(xIn: AcrominDataItem)
}