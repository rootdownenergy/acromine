package com.rootdown.dev.adidev_albertson.ui.feature_search

import android.util.Log
import androidx.lifecycle.*
import com.rootdown.dev.adidev_albertson.data.local.AcrominDataItem
import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.data.repo.SearchRepo
import com.rootdown.dev.adidev_albertson.data.repo.SearchRepoImpl
import com.rootdown.dev.adidev_albertson.util.Event
import com.rootdown.dev.adidev_albertson.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.io.IOException
import java.lang.StringBuilder
import javax.inject.Inject


@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repoImpl: SearchRepo
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
    private lateinit var defaultAcromine: Event<Resource<AcrominDataItem>>

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
    fun saveSearch(lsIn: String="",str:String=""){

        var ls = acromineResult.value?.lfs.toString()
        if(ls.isEmpty()){
            ls = lsIn
        }
        var strIn = acromineResult.value?.sf
        if(strIn!!.isEmpty()){
            strIn = str
        }
        if(ls.isEmpty() && strIn!!.isEmpty()){
            lsInIfNull(lsIn,str)
        }
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
    private fun lsInIfNull(lsIn:String,strIn:String){
        val lsX: MutableList<String> = mutableListOf()
        lsX.add(lsIn)
        defaultAcromine = Event(Resource.error(msg = "missing input", data = AcrominDataItem(id = 1, lfs = lsX, sf = strIn)))
        _acromineData.postValue(defaultAcromine)
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