package com.rootdown.dev.adidev_albertson.lib.interfaces

import com.rootdown.dev.adidev_albertson.data.model.UserLocal
import kotlinx.coroutines.flow.Flow


interface LocalSource {
    suspend fun saveUser(user: UserLocal)
    suspend fun getUser() : Flow<UserLocal>
}
