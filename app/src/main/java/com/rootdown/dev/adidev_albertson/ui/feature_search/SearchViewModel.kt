package com.rootdown.dev.adidev_albertson.ui.feature_search

import android.util.Log
import androidx.lifecycle.*
import com.rootdown.dev.adidev_albertson.data.local.AcrominDataItem
import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.data.repo.SearchRepoImpl
import com.rootdown.dev.adidev_albertson.util.Event
import com.rootdown.dev.adidev_albertson.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.io.IOException
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repoImpl: SearchRepoImpl
) : ViewModel() {
    var count = 0
    var predaciteNum: Int = 0
    var countAcro = 0
    var predicateNum2: Int = 0

    private val _acromineResult = MutableLiveData<Resource<Event<AcromineFull.AcromineFullItem>>>()
    val acromineResultX: LiveData<Resource<Event<AcromineFull.AcromineFullItem>>> = _acromineResult
    val acromineResult = MutableLiveData<AcromineFull.AcromineFullItem>()
    lateinit var savedSearches: LiveData<List<AcrominDataItem>>

    init {
        getAcromineReults("ADD")
        getSearches()
    }

    private fun getSearches() {
        viewModelScope.launch {
            try {
                val x = repoImpl.getSearches()
                savedSearches = x.asLiveData()
            }catch (e: IOException){
                Log.w("ErrIO", "Error retrieving flow of db table: ${e.message}")
            }
        }
    }
    fun getAcromineReults(q: String) {
        viewModelScope.launch {
            try {
                val xx = repoImpl.getAcro(q)
                Log.w("XXX", "Response: $xx")
                acromineResult.postValue(xx)
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
        val ls: String = acromineResult.value?.lfs.toString() ?: "error"
        val strIn: String = acromineResult.value?.sf ?: "error"

        val lsX: MutableList<String> = mutableListOf()
        lsX.add(ls)
        val currSave: AcrominDataItem = AcrominDataItem(lfs = lsX, sf = strIn)
        viewModelScope.launch {
            try {
                repoImpl.saveSearch(currSave)
            }catch (e: IOException){
                Log.w("ERR", "Error on save search: ${e.message}")
            }
        }
    }
    fun deleteSearch(id: Int){
        viewModelScope.launch {
            try {
                repoImpl.deleteAcromineItem(id)
            }catch (e: IOException){
                Log.w("Err", "Could not delete acromine search item: ${e.message}")
            }
        }
    }
}