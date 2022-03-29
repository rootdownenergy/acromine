package com.rootdown.dev.adidev_albertson.data.net

import com.rootdown.dev.adidev_albertson.data.repo.SearchRepoImpl
import com.rootdown.dev.adidev_albertson.ui.feature_search.SearchViewModel
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.mockito.MockitoAnnotations

class RetrofitTestCall(val api: ApiService) {

    private lateinit var vm: SearchViewModel
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        val repo = SearchRepoImpl(api)

        vm = SearchViewModel(repo)

        val mockWebServer = MockWebServer()
        mockWebServer.start()
    }
}