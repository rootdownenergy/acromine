package com.rootdown.dev.adidev_albertson.lib.abstracts

import androidx.security.crypto.MasterKeys

abstract class AuthManager{

    /*
    *  assign access level to current auth manager
    * */
    abstract fun tieSysExposure()
}