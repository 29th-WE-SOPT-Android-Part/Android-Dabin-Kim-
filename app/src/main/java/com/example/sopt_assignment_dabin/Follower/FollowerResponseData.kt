package com.example.sopt_assignment_dabin.Follower

import com.google.gson.annotations.SerializedName

data class FollowerResponseData(
    @SerializedName("login")
    val followerId: String,
    @SerializedName("avatar_url")
    val followerProfile: String,
)

data class FollowerResponseDataBio(
    @SerializedName("bio")
    val followerBio: String,
)
data class FollowerResponseDataBio2(
    val followerId: String,
    val followerProfile: String,
    val followerBio: String,
)