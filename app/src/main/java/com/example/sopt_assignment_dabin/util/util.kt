package com.example.sopt_assignment_dabin.util

import android.content.Context
import android.widget.Toast
import com.example.sopt_assignment_dabin.data.local.Room.RoomDAO
import com.example.sopt_assignment_dabin.data.local.Room.RoomHelper

object MyUtil {
    fun Context.shortToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun Context.getHelper(): RoomDAO {
        return RoomHelper.getInstance(applicationContext)!!.roomInfoDao()
    }
}
