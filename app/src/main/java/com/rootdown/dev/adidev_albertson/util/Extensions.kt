package com.rootdown.dev.adidev_albertson.util

import android.annotation.SuppressLint
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.google.android.material.snackbar.Snackbar


fun Snackbar.action(text: String, @ColorRes color: Int? = null, listener: (View) -> Unit) {
    setAction(text, listener)
    color?.let { setActionTextColor(Color.Cyan.green.toInt()) }
}

