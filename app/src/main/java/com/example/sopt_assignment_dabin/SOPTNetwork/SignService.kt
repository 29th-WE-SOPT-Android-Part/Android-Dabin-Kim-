package com.example.sopt_assignment_dabin.SOPTNetwork

import com.example.sopt_assignment_dabin.Sign.data.SigninRequestData
import com.example.sopt_assignment_dabin.Sign.data.SigninResponseData
import com.example.sopt_assignment_dabin.Sign.data.SignupRequestData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignupService {
    @Headers("Content-Type:application/json")
    @POST("user/signup")
    fun signupLogin(
        @Body body: SignupRequestData
    ): Call<SiginupResponseData.Data>
}

interface SigninService {
    @Headers("Content-Type:application/json")
    @POST("user/login")
    fun signinLogin(
        @Body body: SigninRequestData
    ): Call<SigninResponseData.Data>
}