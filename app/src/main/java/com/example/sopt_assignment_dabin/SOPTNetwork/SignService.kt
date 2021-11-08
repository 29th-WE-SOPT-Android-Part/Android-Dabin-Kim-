package com.example.sopt_assignment_dabin.SOPTNetwork

import com.example.sopt_assignment_dabin.Sign.data.SignResponseWrapperData
import com.example.sopt_assignment_dabin.Sign.data.SigninRequestData
import com.example.sopt_assignment_dabin.Sign.data.SigninResponseData
import com.example.sopt_assignment_dabin.Sign.data.SignupRequestData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import java.sql.Wrapper

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