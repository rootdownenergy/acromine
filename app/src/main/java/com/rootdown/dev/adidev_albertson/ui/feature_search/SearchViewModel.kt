package com.rootdown.dev.adidev_albertson.ui.feature_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rootdown.dev.adidev_albertson.data.model.Acromine
import com.rootdown.dev.adidev_albertson.data.repo.SearchRepo
import dagger.hilt.android.lifecycle.HiltViewModel


@HiltViewModel
class SearchViewModel(
    private val repo: SearchRepo
) : ViewModel() {
    private lateinit var ini: LiveData<Acromine>
    init {
        ini = getIniResult()
    }
    private fun getIniResult() : LiveData<Acromine> {
        TODO()
    }
}