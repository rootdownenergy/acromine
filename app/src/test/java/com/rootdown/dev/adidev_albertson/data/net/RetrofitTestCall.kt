package com.rootdown.dev.adidev_albertson.data.net

import okhttp3.ResponseBody
import org.junit.Test
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import strikt.api.*
import strikt.api.expectThat


class RetrofitTestCall(val api: ApiService) {

    @Test
    fun `it should GET with query`() {

        val remoteApi = api
        val givenSearchQuery = "add"
        val call: Call<ResponseBody> = remoteApi.searchByPhrase(givenSearchQuery)
        expectThat(call.request()) {
            assertThat("is GET method") {
                it.method == "GET"
            }
            assertThat("has given search query") {
                it.url.queryParameterValues("search") == listOf(givenSearchQuery)
            }
        }
    }
}