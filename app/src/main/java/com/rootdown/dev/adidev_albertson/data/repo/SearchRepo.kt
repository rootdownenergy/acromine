package com.rootdown.dev.adidev_albertson.data.repo

import com.rootdown.dev.adidev_albertson.data.model.Acromine
import com.rootdown.dev.adidev_albertson.data.remote.RemoteDataSource
import javax.inject.Inject

class SearchRepo @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {
    fun getAcromineSaerchResult(query: String) = remoteDataSource.loadAcromineSearch(query)
}