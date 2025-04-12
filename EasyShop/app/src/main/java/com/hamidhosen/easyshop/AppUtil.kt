package com.hamidhosen.easyshop

import android.content.Context
import android.widget.Toast

object AppUtil {
    fun showTest(context: Context, message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }
}