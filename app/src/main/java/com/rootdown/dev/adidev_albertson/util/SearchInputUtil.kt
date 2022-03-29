package com.rootdown.dev.adidev_albertson.util

object SearchInputUtil {
    private val existingAcro = listOf<String>("HMM","ADD")
    /**
     * the input is not valid if the
     * ...search input in empty
     * ... if not acronym exists
     */
    fun validateSearchUserInput(
        searchQuery: String,
    ): Boolean {
        if(searchQuery.isEmpty()){
            return false
        }
        if(searchQuery.isNotEmpty() && searchQuery in existingAcro){
            return true
        }
        return true
    }
}