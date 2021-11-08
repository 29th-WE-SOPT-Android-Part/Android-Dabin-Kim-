package com.example.sopt_assignment_dabin.SOPTNetwork

import com.example.sopt_assignment_dabin.Sign.data.SignResponseWrapperData
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object SignServiceCreator {
    private val BASE_URL = "https://asia-northeast3-we-sopt-29.cloudfunctions.net/api/"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient(AppInterceptor()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val signupService: SignupService = retrofit.create(SignupService::class.java)
    val signinService: SigninService = retrofit.create(SigninService::class.java)
}


private fun provideOkHttpClient(
    interceptor: AppInterceptor
): OkHttpClient = OkHttpClient.Builder()
    .run {
        addInterceptor(interceptor)
        build()
    }

class AppInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain)
            : okhttp3.Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .build()

        proceed(newRequest)
    }
}