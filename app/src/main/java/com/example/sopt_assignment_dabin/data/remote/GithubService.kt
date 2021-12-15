package com.example.sopt_assignment_dabin.data.remote

import com.example.sopt_assignment_dabin.follower.FollowerResponseData
import com.example.sopt_assignment_dabin.follower.FollowerResponseDataBio
import com.example.sopt_assignment_dabin.data.local.RepositoryResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("/users/{username}/repos")
    fun githubRepoGet(
        @Path("username") username: String
    ): Call<List<RepositoryResponseData>>


    @GET("/users/{username}/followers")
    fun githubFollowerGet(
        @Path("username") username: String
    ): Call<List<FollowerResponseData>>


    @GET("/users/{username}")
    fun githubBioGet(
        @Path("username") username: String
    ): Call<FollowerResponseDataBio>
}