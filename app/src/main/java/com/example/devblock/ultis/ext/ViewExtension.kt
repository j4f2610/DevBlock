package com.example.devblock.ultis

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Created by Viktor on 31,May,2020
 */

fun View.showSnackBar(message: String) {
    val snackBar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackBar.show()
}