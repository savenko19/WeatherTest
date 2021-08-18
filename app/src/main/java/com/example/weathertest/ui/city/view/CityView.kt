package com.example.weathertest.ui.city.view

import com.example.weathertest.domain.model.WeatherOfCity
import com.example.weathertest.ui.mvp.MvpView

interface CityView : MvpView {

    fun showWeather(weather: WeatherOfCity)
}