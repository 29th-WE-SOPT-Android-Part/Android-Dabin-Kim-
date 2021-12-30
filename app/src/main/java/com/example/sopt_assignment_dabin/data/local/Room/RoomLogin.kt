package com.example.sopt_assignment_dabin.data.local.Room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "table_login")
class RoomLogin {
    @ColumnInfo
    var onBording = true

    @ColumnInfo
    var autoLogin = false

    @PrimaryKey
    @ColumnInfo
    var id = ""

    @ColumnInfo
    var password = ""

    constructor(onBording: Boolean = true, autoLogin: Boolean = false, id: String, password: String) {
        this.onBording = onBording
        this.autoLogin = autoLogin
        this.id = id
        this.password = password
    }
}