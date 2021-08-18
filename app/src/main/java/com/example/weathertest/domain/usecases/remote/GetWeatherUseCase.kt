package com.example.weathertest.domain.usecases.remote

import com.example.weathertest.domain.repository.RemoteRepository

class GetWeatherUseCase(private val remoteRepository: RemoteRepository) {

    operator fun invoke(lat: Long, lon: Long) = remoteRepository.getWeather(lat, lon)
}