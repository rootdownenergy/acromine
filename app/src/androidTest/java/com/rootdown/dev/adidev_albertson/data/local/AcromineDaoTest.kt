package com.rootdown.dev.adidev_albertson.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import com.rootdown.dev.adidev_albertson.getOrAwaitValueTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class AcromineDaoTest {

    //rule
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    /*
    * ini db
    * ini dao
    * */
    private lateinit var database: AppDatabase
    private lateinit var dao: AcromineDao


    // config test
    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.acromineDao()
    }
    @Test
    fun insertAcromineItem() = runTest{
        val ls: MutableList<String> = mutableListOf("jfhpisuadh","hpiuerh")
        val item = AcrominDataItem(id = 1, lfs = ls, sf = "DFSDf")
        dao.insertAll(item)

        val allAcromine = dao.getAcromine().asLiveData().getOrAwaitValueTest()

        assertThat(allAcromine).contains(item)
    }
    @Test
    fun deleteAcromineItem() = runTest {
        val ls: MutableList<String> = mutableListOf("jfhpisuadh","hpiuerh")
        val item: AcrominDataItem = AcrominDataItem(id = 1, lfs = ls, sf = "DFSDf")
        dao.insertAll(item)
        dao.deleteAcromineItem(item.id)
        val allAcromine = dao.getAcromine().asLiveData().getOrAwaitValueTest()
        assertThat(allAcromine).doesNotContain(item)
    }
    @Test
    fun getAcromineItemById() = runTest {
        val ls: MutableList<String> = mutableListOf("jfhpisuadh","hpiuerh")
        val item = AcrominDataItem(id = 1, lfs = ls, sf = "DFSDf")
        val item2  = AcrominDataItem(id = 2, lfs = ls, sf = "D345")
        val item3  = AcrominDataItem(id = 3, lfs = ls, sf = "Der245")
        dao.insertAll(item)
        dao.insertAll(item2)
        dao.insertAll(item3)
        val get2 = dao.acromineById(item2.id).getOrAwaitValueTest()
        assertThat(get2.id).isEqualTo(item2.id)
    }
    @After
    fun teardown(){
        database.close()
    }
}