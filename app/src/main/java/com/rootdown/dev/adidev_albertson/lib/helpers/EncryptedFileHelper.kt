package com.rootdown.dev.adidev_albertson.lib.helpers

import android.content.Context
import androidx.security.crypto.EncryptedFile

import androidx.security.crypto.MasterKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStream
import java.security.DigestInputStream
import java.security.MessageDigest


class EncryptedFileHelper