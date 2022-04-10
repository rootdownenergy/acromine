package com.rootdown.dev.adidev_albertson.data.source

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import com.rootdown.dev.adidev_albertson.data.local.AcrominDataItem
import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.data.repo.SearchRepo
import kotlinx.coroutines.flow.Flow
import kotlin.math.sin

class FakeSearchRepo : SearchRepo {
    private val acromineDataItem = mutableListOf<AcrominDataItem>()
    private val acromineResponse = mutableListOf<AcromineFull.AcromineFullItem>()
    private val observableDataItem = MutableLiveData<List<AcrominDataItem>>(acromineDataItem)
    val currentAcrominDataItem = AcrominDataItem(id = 1, lfs = mutableListOf("TDD","TDDX"), sf = "TDD")
    private var eMsg = "error the current acromine data item not found"
    private var flagger = false

    private var shouldReturnNetworkError = false
    fun shouldReturnNetError(value: Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        observableDataItem.postValue(acromineDataItem)
    }


    override suspend fun getAcro(q: String): AcromineFull.AcromineFullItem {
        return AcromineFull.AcromineFullItem(sf = "TDD", lfs = listOf(AcromineFull.AcromineFullItem.Lf(freq = 123, lf = "TDD", since = 1985,
            listOf(AcromineFull.AcromineFullItem.Lf.Var(freq = 123, lf = "TDD", since = 1985)))))
    }

    override suspend fun saveSearch(xIn: AcrominDataItem) {
        acromineDataItem.add(xIn)
    }

    override suspend fun getSearches(): Flow<List<AcrominDataItem>> {
        return observableDataItem.asFlow()
    }

    override suspend fun deleteAcromineItem(id: Int) {
        if(acromineDataItem.contains(currentAcrominDataItem)) {
            acromineDataItem.remove(currentAcrominDataItem)
            refreshLiveData()
        } else {
            flagger = true
        }
    }

}