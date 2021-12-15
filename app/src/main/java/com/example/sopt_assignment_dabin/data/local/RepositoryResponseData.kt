package com.example.sopt_assignment_dabin.repository

import com.google.gson.annotations.SerializedName

data class RepositoryResponseData(
    @SerializedName("name")
    val repoName: String,
    @SerializedName("full_name")
    val reposUrl: String
)

