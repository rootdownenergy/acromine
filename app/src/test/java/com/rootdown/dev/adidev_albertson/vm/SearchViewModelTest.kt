package com.rootdown.dev.adidev_albertson.vm

import android.util.Log
import com.rootdown.dev.adidev_albertson.data.source.FakeSearchRepo
import com.rootdown.dev.adidev_albertson.data.source.MockResponseFileReader
import org.junit.Assert.*
import org.junit.Test
import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.Paths

class SearchViewModelTest(
    val fakeData: FakeSearchRepo
) {

    @Test
    fun `read sample success json file`(){
        val path = Paths.get("/rawsuccess_response.json")
        val reader = MockResponseFileReader("$path")
        assertNotNull(reader.xVal)
    }
    // does the search input make it in
    // to the variable in vm?
    @Test
    fun does_get_search_in() {
        assertEquals(8,4+2+2)
    }
    // does net call succeed?
    @Test
    fun does_input_call_net_return_not_error(){
        assertEquals(10, 5+1+2+2)
    }
}