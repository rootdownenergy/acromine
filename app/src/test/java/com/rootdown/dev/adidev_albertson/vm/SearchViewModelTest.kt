package com.rootdown.dev.adidev_albertson.vm


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.google.common.truth.Truth.assertThat
import com.rootdown.dev.adidev_albertson.data.model.AcromineFull
import com.rootdown.dev.adidev_albertson.data.model.remote.AcromineSearchResult
import com.rootdown.dev.adidev_albertson.data.net.ApiService
import com.rootdown.dev.adidev_albertson.data.source.FakeSearchRepo
import com.rootdown.dev.adidev_albertson.data.source.MockResponseFileReader
import com.rootdown.dev.adidev_albertson.ui.feature_search.SearchViewModel
import com.rootdown.dev.adidev_albertson.util.*
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlinx.coroutines.test.runTest
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchViewModelTest() {

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var vm: SearchViewModel

    @Mock
    private lateinit var api: ApiService

    @Mock
    private lateinit var apiAcroObserver: Observer<AcromineFull.AcromineFullItem>

    private val testData = javaClass.getResource("src/test/resources/success_response.json")?.path ?: ""
    private val testDataFailed = javaClass.getResource("src/test/resources/failed_response.json")?.path ?: ""

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        api = ApiService.getAcro()
        vm = SearchViewModel(FakeSearchRepo())
        vm.getAcromineReults("TDD")
        vm.acromineResult.observeForever(apiAcroObserver)
    }
    @Test
    fun `read sample success json file`(){
        val reader = MockResponseFileReader(testData)
        TestCase.assertNotNull(reader.xVal)
    }
    @Test
    fun `read sample success json and ensure contains json`(){
        val reader = this.javaClass.classLoader?.getResource(testData)
        assertThat(reader?.content).isNotNull()
    }
    @Test
    fun `read sample failed json file`(){
        val reader = MockResponseFileReader(testDataFailed)
        TestCase.assertNotNull(reader.xVal)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `livedata initial results not null returns true`() = runTest {
        val t = vm.acromineResult.getOrAwaitValue()
        assertThat(t.lfs).isNotEmpty()
    }

}