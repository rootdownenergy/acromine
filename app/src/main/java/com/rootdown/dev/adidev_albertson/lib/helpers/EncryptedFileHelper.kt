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


class EncryptedFileHelper(private val context: Context) {

    var masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)

    var file: File = File(context.getFilesDir(), "secret_data")
    var encryptedFile = EncryptedFile.Builder(
        file,
        context,
        masterKeyAlias,
        EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
    ).build()

    // write to the encrypted file
    var encryptedOutputStream: FileOutputStream = encryptedFile.openFileOutput()

    // read the encrypted file
    var encryptedInputStream: FileInputStream = encryptedFile.openFileInput()

    private suspend fun extSourceCheck(stream: InputStream){

        val hash = withContext(Dispatchers.IO){
            calculateHash(stream)
        }
        // Store "expectedHash" in a secure location.

    }
    // Calculating the hash code can take quite a bit of time, so it shouldn't
    // be done on the main thread.
    suspend fun calculateHash(stream: InputStream): String {
        return withContext(Dispatchers.IO) {
            val digest = MessageDigest.getInstance("SHA-512")
            val digestStream = DigestInputStream(stream, digest)
            while (digestStream.read() != -1) {
                // The DigestInputStream does the work; nothing for us to do.
            }
            digest.digest().joinToString(":") { "%02x".format(it) }
        }
    }
}