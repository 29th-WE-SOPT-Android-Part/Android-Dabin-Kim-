package com.example.sopt_assignment_dabin.data.local


data class SignResponseWrapperData<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T? = null
)

