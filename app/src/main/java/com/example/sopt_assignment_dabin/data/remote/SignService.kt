package com.example.sopt_assignment_dabin.sopt

import com.example.sopt_assignment_dabin.data.data.SignResponseWrapperData
import com.example.sopt_assignment_dabin.data.data.SigninRequestData
import com.example.sopt_assignment_dabin.data.data.SigninResponseData
import com.example.sopt_assignment_dabin.data.data.SignupRequestData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupService {
    @POST("user/signup")
    fun signupLogin(
        @Body body: SignupRequestData
    ): Call<SignResponseWrapperData<SiginupResponseData>>
}

interface SigninService {
    @POST("user/login")
    fun signinLogin(
        @Body body: SigninRequestData
    ): Call<SignResponseWrapperData<SigninResponseData>>
}