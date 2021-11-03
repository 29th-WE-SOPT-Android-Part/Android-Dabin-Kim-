package com.example.sopt_assignment_dabin.GithubNetwork

import com.google.gson.annotations.SerializedName

data class GithubResponseData(
    @SerializedName("name")
    val repoName: String,
    @SerializedName("full_name")
    val fullRepoName: String
)