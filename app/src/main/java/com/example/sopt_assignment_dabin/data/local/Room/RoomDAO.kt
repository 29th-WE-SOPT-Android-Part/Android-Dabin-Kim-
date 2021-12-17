package com.example.sopt_assignment_dabin.data.local.Room

import androidx.room.*
import retrofit2.http.DELETE

@Dao
interface RoomDAO {

    //유저 정보 만들기
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(login: RoomLogin)

    //유저 정보 얻기
    @Query("SELECT * FROM table_login ")
    fun get(): RoomLogin

    //자동로그인 선택시 true로 바꾸고 정보 업데이트
    @Query("UPDATE table_login SET autoLogin =:autoLogin,id=:id,password=:password")
    fun updateInfo(autoLogin: Boolean, id: String, password: String)

    //설정에서 자동로그인 해제 시 false로 바꾸기
    @Query("UPDATE table_login SET autoLogin =:autoLogin")
    fun updateLogin(autoLogin: Boolean)

    //설정에서 자동로그인 해제 시 유저정보 삭제
    @Query("DELETE FROM table_login WHERE id = :id")
    fun delete(id: String)
}