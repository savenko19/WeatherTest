package com.example.weathertest.domain.model

data class WeatherOfCity(
    val currentTemp: Int,
    val currentCondition: String,
    val conditionIconPath: String,
    val hourlyForecast: Map<String, Int>,
    val datesForecasts: Map<String, Int>
)