package com.rootdown.dev.adidev_albertson.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.google.gson.annotations.Expose

class AcromineFull : ArrayList<AcromineFull.AcromineFullItem>(){
    @JsonClass(generateAdapter = true)
    data class AcromineFullItem(
        @Json(name = "lfs")
        @Expose
        val lfs: List<Lf?>?,
        @Json(name = "sf")
        @Expose
        val sf: String? // HMM
    ) {
        @JsonClass(generateAdapter = true)
        data class Lf(
            @Json(name = "freq")
            @Expose
            val freq: Int?, // 267
            @Json(name = "lf")
            @Expose
            val lf: String?, // heavy meromyosin
            @Json(name = "since")
            @Expose
            val since: Int?, // 1971
            @Json(name = "vars")
            @Expose
            val vars: List<Var?>?
        ) {
            @JsonClass(generateAdapter = true)
            data class Var(
                @Json(name = "freq")
                @Expose
                val freq: Int?, // 244
                @Json(name = "lf")
                @Expose
                val lf: String?, // heavy meromyosin
                @Json(name = "since")
                @Expose
                val since: Int? // 1971
            )
        }
    }
}