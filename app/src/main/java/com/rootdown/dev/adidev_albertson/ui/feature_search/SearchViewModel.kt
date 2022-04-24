package com.rootdown.dev.adidev_albertson.ui.feature_search

import android.util.Log
import androidx.lifecycle.*
import com.rootdown.dev.adidev_albertson.data.local.AcrominDataItem
import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.data.repo.SearchRepo
import com.rootdown.dev.adidev_albertson.util.Event
import com.rootdown.dev.adidev_albertson.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repoImpl: SearchRepo,
    private val defaultCoroutineDispatcher: CoroutineDispatcher
) : ViewModel() {
    var count = 0
    var predaciteNum: Int = 0
    var countAcro = 0
    var predicateNum2: Int = 0

    private val _acromineResult = MutableLiveData<AcromineFull.AcromineFullItem>()
    val acromineResult: LiveData<AcromineFull.AcromineFullItem> = _acromineResult

    private val _acromineData = MutableLiveData<Event<Resource<AcrominDataItem>>>()
    val acromineData: LiveData<Event<Resource<AcrominDataItem>>> = _acromineData

    lateinit var savedSearches: LiveData<List<AcrominDataItem>>

    init {
        getAcromineResults("TDD")
        getSearches()
    }
    private fun getSearches() {
        viewModelScope.launch(defaultCoroutineDispatcher) {
            try {
                val x = repoImpl.getSearches()
                savedSearches = x.asLiveData()
            }catch (e: IOException){
                Log.w("ErrIO", "Error retrieving flow of db table: ${e.message}")
            }
        }
    }
    fun getAcromineResults(q: String) {
        viewModelScope.launch(defaultCoroutineDispatcher) {
            try {
                val xx = repoImpl.getAcro(q)
                _acromineResult.postValue(xx)
            } catch (netE: IOException) {
                Log.w("ERRORXXX", netE.message.toString())
            }
        }
    }
    fun makeIds(i: Int){
        count++
        if(count<=i){makeIds(predaciteNum)}
    }
    fun saveSearch(){
        val ls = acromineResult.value?.lfs.toString() ?: "error"
        val strIn = acromineResult.value?.sf ?: "error"
        val lsX: MutableList<String> = mutableListOf()
        lsX.add(ls)
        val currSave = AcrominDataItem(lfs = lsX, sf = strIn)
        viewModelScope.launch(defaultCoroutineDispatcher) {
            try {
                repoImpl.saveSearch(currSave)
            }catch (e: IOException){
                Log.w("ERR", "Error on save search: ${e.message}")
            }
        }
    }
    fun deleteSearch(id: Int){
        viewModelScope.launch(defaultCoroutineDispatcher) {
            try {
                repoImpl.deleteAcromineItem(id)
            }catch (e: IOException){
                Log.w("Err", "Could not delete acromine search item: ${e.message}")
            }
        }
    }
}