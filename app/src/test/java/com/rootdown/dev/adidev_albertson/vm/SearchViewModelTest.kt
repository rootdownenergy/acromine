package com.rootdown.dev.adidev_albertson.vm

import android.util.Log
import com.google.common.truth.Truth.assertThat
import com.rootdown.dev.adidev_albertson.data.source.FakeSearchRepo
import com.rootdown.dev.adidev_albertson.ui.feature_search.SearchViewModel
import com.rootdown.dev.adidev_albertson.util.Status
import com.rootdown.dev.adidev_albertson.util.getOrAwaitValueTest
import org.junit.Before
import org.junit.Test


class SearchViewModelTest(
    private val fakeData: FakeSearchRepo
) {

    private lateinit var vm: SearchViewModel

    @Before
    fun setup() {
        vm = SearchViewModel(FakeSearchRepo())
    }

    @Test
    fun `insert search input with missing input, returns error`(){
        vm.saveSearch(lsIn = "TDD", str = "")
        val t = vm.acromineData.getOrAwaitValueTest()
        assertThat(t.getIfNothandled()?.status).isEqualTo(Status.ERROR)
    }
    @Test
    fun `insert search, returns success`(){
        vm.saveSearch(lsIn = "TDD", str = "")
        val t = vm.acromineData.getOrAwaitValueTest()
        assertThat(t.getIfNothandled()?.status).isEqualTo(Status.ERROR)
    }

}