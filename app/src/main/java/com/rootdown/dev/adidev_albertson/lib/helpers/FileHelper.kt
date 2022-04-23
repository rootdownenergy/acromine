package com.rootdown.dev.adidev_albertson.lib.helpers

import java.io.File
import java.nio.file.Paths

class FileHelper(path: String) {
    private val cacheDir = Paths.get(path).toAbsolutePath().toString()
    val cacheFile = File(cacheDir)
}