package com.example.sopt_assignment_dabin.GithubNetwork

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object GithubServiceCreator {
    private val BASE_URL = "https://api.github.com"
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient(AppInterceptorGit()))
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val githubService: GithubService = retrofit.create(GithubService::class.java)
}

private fun provideOkHttpClient(
    interceptor: AppInterceptorGit
): OkHttpClient = OkHttpClient.Builder()
    .run {
        addInterceptor(interceptor)
        build()
    }

class AppInterceptorGit : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain)
            : okhttp3.Response = with(chain) {
        val newRequest = request().newBuilder()
            .addHeader("accept", "application/vnd.github.v3+json")
            .addHeader("Authorization", "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")
            .build()

        proceed(newRequest)
    }
}