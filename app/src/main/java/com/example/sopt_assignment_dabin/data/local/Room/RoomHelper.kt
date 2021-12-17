package com.example.sopt_assignment_dabin.data.local.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sopt_assignment_dabin.User


@Database(entities = [RoomLogin::class], version = 1, exportSchema = false)
abstract class RoomHelper : RoomDatabase() {
    abstract fun roomInfoDao(): RoomDAO

    companion object {
        private var instance: RoomHelper? = null

        @Synchronized
        fun getInstance(context: Context): RoomHelper? {
            if (instance == null) {
                synchronized(RoomHelper::class) {
                    instance = Room.databaseBuilder(
                        context,
                        RoomHelper::class.java,
                        "login_database"
                    ).build()
                }
            }
            return instance
        }
    }
}