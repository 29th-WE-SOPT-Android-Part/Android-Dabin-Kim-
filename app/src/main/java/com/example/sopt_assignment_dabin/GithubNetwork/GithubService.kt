package com.example.sopt_assignment_dabin.GithubNetwork

import com.example.sopt_assignment_dabin.Follower.FollowerResponseData
import com.example.sopt_assignment_dabin.Follower.FollowerResponseDataBio
import com.example.sopt_assignment_dabin.Repository.RepositoryResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface GithubService {
    @Headers("accept:application/vnd.github.v3+json", "Authorization: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")
    @GET("/users/{username}/repos")
    fun githubRepoGet(
        @Path("username") username: String
    ): Call<List<RepositoryResponseData.Data>>

    @Headers("accept:application/vnd.github.v3+json", "Authorization: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")
    @GET("/users/{username}/followers")
    fun githubFollowerGet(
        @Path("username") username: String
    ): Call<List<FollowerResponseData>>

    @Headers("accept:application/vnd.github.v3+json", "Authorization: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")
    @GET("/users/{username}")
    fun githubBioGet(
        @Path("username") username: String
    ): Call<FollowerResponseDataBio>
}