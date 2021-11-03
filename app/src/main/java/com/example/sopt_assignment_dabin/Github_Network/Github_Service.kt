package com.example.sopt_assignment_dabin.Github_Network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("/orgs/{org}/repos")
    fun getRepoList(
        @Path("org")
        userName:String
    ):Call<List<GithubResponseData>>

}