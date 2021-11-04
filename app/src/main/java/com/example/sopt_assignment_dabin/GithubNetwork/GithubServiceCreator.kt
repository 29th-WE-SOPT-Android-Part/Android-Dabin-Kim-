package com.example.sopt_assignment_dabin.GithubNetwork

import com.example.sopt_assignment_dabin.SOPTNetwork.SigninService
import com.example.sopt_assignment_dabin.SOPTNetwork.SignupService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GithubServiceCreator {
    private val BASE_URL = "https://api.github.com"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val githubService: GithubService = retrofit.create(GithubService::class.java)
}