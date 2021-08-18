package com.example.weathertest.data.remote.provider

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {

    private var mRetrofit: Retrofit? = null

    fun getRetrofit() = mRetrofit

    init {

        mRetrofit = Retrofit.Builder()
            .baseUrl("https://api.weather.yandex.ru/")
            .client(createClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    private fun createClient(): OkHttpClient {

        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(object : Interceptor {
                override fun intercept(chain: Interceptor.Chain): Response {
                    val request = chain.request().newBuilder()
                        .addHeader(
                            "X-Yandex-API-Key",
                            "69152427-0559-40fe-8427-aec50986927b"
                        ).build()
                    return chain.proceed(request)
                }
            })
            .build()
    }
}