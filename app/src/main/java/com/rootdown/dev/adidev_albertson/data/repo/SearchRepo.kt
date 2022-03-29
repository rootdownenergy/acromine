package com.rootdown.dev.adidev_albertson.data.repo

import com.rootdown.dev.adidev_albertson.data.model.AcromineFull

interface SearchRepo {
    suspend fun getAcro(q: String): AcromineFull.AcromineFullItem
}