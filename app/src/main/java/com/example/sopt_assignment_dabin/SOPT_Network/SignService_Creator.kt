package com.example.sopt_assignment_dabin.SOPT_Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object SignServiceCreator {
    private val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signupService:SignupService= retrofit.create(SignupService::class.java)
    val signinService:SigninService= retrofit.create(SigninService::class.java)
}