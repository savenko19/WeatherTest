package com.example.weathertest.data.remote.api

import com.example.weathertest.data.remote.dto.RemoteWeather
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherApi {

    @GET("v2/forecast?")
    @Headers("Content-Type: application/json")
    fun getWeather(
        @Query("lat")
        lat: Long,
        @Query("lon")
        lon: Long
    ): Single<RemoteWeather>
}