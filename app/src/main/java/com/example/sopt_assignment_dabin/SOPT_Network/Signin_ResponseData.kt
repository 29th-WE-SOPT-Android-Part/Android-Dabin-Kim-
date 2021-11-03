package com.example.sopt_assignment_dabin.SOPT_Network

data class SigninResponseData(
    val status: Int,
    val success: Boolean,
    val message: String,
) {
    data class Data(
        val id: Int,
        val name: String,
        val email: String
    )
}