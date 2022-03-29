package com.rootdown.dev.adidev_albertson.data.source

import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.data.repo.SearchRepo

class FakeSearchRepo() : SearchRepo {

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    override suspend fun getAcro(q: String): AcromineFull.AcromineFullItem {
        TODO("Not yet implemented")
    }

}