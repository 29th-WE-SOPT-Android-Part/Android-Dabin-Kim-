package com.example.sopt_assignment_dabin.Github_Network

import com.google.gson.annotations.SerializedName

data class GithubResponseData(
    @SerializedName("name")
    val repoName: String,
    @SerializedName("full_name")
    val fullRepoName: String
)