package com.example.weathertest.data.mapper

import com.example.weathertest.data.remote.dto.RemoteWeather
import com.example.weathertest.domain.model.WeatherOfCity

class WeatherMapper :
    Mapper<RemoteWeather, WeatherOfCity> {
    override fun map(input: RemoteWeather): WeatherOfCity {
        val hourlyForecast = HashMap<String, Int>()
        val datesForecasts = HashMap<String, Int>()
        for (forecast in input.forecasts) {
            datesForecasts[forecast.date] = forecast.parts.day.averageTemp
            for (hour in forecast.hours) {
                hourlyForecast[hour.hour] = hour.temp
            }
        }

        return WeatherOfCity(
            input.fact.temp,
            input.fact.condition,
            input.fact.icon,
            hourlyForecast,
            datesForecasts
        )
    }
}