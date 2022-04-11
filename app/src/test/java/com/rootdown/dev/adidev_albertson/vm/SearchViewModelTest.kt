package com.rootdown.dev.adidev_albertson.vm


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.rootdown.dev.adidev_albertson.data.source.FakeSearchRepo
import com.rootdown.dev.adidev_albertson.ui.feature_search.SearchViewModel
import com.rootdown.dev.adidev_albertson.util.Status
import com.rootdown.dev.adidev_albertson.util.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlinx.coroutines.test.runTest

@ExperimentalCoroutinesApi
class SearchViewModelTest() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var vm: SearchViewModel

    @Before
    fun setup() {
        vm = SearchViewModel(FakeSearchRepo())
    }

    @Test
    fun `insert search input with missing input, returns error`(){
        val t = vm.acromineData.getOrAwaitValueTest()
        assertThat(t.getIfNothandled()?.status).isEqualTo(Status.ERROR)
    }

}