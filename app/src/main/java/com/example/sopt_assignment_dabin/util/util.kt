package com.example.sopt_assignment_dabin.util

import android.content.Context
import android.widget.Toast

object util {
    fun Context.shortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
