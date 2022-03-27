package com.rootdown.dev.adidev_albertson.data.model

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Vars(
    var lf: String? = null,
    var freq: Int? = null,
    var since: Int? = null,
) : Parcelable