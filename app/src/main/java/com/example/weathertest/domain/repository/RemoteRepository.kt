package com.example.weathertest.domain.repository

import com.example.weathertest.domain.model.WeatherOfCity
import io.reactivex.rxjava3.core.Single

interface RemoteRepository {

    fun getWeather(lat: Long, lon: Long): Single<WeatherOfCity>
}