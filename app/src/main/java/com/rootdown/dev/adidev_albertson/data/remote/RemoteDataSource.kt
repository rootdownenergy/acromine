package com.rootdown.dev.adidev_albertson.data.remote

import com.rootdown.dev.adidev_albertson.data.net.AcromineService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(val apiService: AcromineService) {
    fun loadAcromineSearch(query: String) = apiService.loadAcromine(query)
}