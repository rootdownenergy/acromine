package com.rootdown.dev.adidev_albertson.ui.feature_search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.data.repo.SearchRepoImpl
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

    val acromineResult = MutableLiveData<AcromineFull.AcromineFullItem>()

    init {
        getAcromineReults("ADD")
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

}