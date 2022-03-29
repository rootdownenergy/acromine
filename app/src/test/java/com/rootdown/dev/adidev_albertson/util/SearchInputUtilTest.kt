package com.rootdown.dev.adidev_albertson.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class SearchInputUtilTest {

    @Test
    fun `empty user name returns false`(){
        val result = SearchInputUtil.validateSearchUserInput(
            searchQuery = ""
        )
        assertThat(result).isFalse()
    }
    @Test
    fun `input is not an acromine result`(){
        val result = SearchInputUtil.validateSearchUserInput(
            searchQuery = "ASDASDLKQ"
        )
        assertThat(result).isTrue()
    }
    @Test
    fun `valid search input and is acromine returns true`(){
        val result = SearchInputUtil.validateSearchUserInput(
            searchQuery = "HMM"
        )
        assertThat(result).isTrue()
    }

}