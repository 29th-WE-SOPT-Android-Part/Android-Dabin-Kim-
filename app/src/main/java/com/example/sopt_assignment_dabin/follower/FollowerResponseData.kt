package com.example.sopt_assignment_dabin.follower

import com.google.gson.annotations.SerializedName

data class FollowerResponseData(
    @SerializedName("login")
    val followerId: String,
    @SerializedName("avatar_url")
    val followerProfile: String
)

data class FollowerResponseDataBio(
val bio: String?
)

data class FollowerData(
    val followerId: String,
    val followerProfile: String,
    val followerBio: String
)