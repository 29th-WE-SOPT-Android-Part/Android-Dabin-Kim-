package com.example.sopt_assignment_dabin.GithubNetwork

import com.example.sopt_assignment_dabin.Follower.FollowerResponseData
import com.example.sopt_assignment_dabin.Follower.FollowerResponseDataBio
import com.example.sopt_assignment_dabin.Repository.RepositoryResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
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