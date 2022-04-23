package com.rootdown.dev.adidev_albertson.lib.interfaces

import javax.crypto.KeyGenerator


interface Auth {

    suspend fun registerToken() : KeyGenerator
}