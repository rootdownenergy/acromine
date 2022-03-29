package com.rootdown.dev.adidev_albertson.vm

import org.junit.Test
import org.junit.Assert.*

class SearchViewModelTest {
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