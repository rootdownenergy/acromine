package com.rootdown.dev.adidev_albertson.data.source

import java.io.InputStreamReader

class MockResponseFileReader(path: String) {
    var xVal: String
    init {
        val reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(path))
        xVal = reader.readText()
        reader.close()
    }
}