package com.rootdown.dev.adidev_albertson.data.source

import com.rootdown.dev.adidev_albertson.data.net.ApiService
import org.mockito.Mockito

class FakeDataSource(val service: ApiService) {
    val mockApiService = Mockito.mock(ApiService::class.java)
}