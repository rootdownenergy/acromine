package com.rootdown.dev.adidev_albertson.lib.abstracts

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

abstract class DataStoreManager {
    abstract fun defineDataStoreDataItem(): DataStore<Preferences>
}