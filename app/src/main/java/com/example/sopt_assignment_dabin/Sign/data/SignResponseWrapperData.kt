package com.example.sopt_assignment_dabin.Sign.data


data class SignResponseWrapperData<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T? = null
)

