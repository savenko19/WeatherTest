package com.example.weathertest.ui.city.presenter

import com.example.weathertest.domain.model.WeatherOfCity
import com.example.weathertest.ui.city.view.CityView
import com.example.weathertest.ui.mvp.MvpPresenter

interface CityPresenter : MvpPresenter<CityView> {

    fun getWeatherOfCity(lat: Long, lon: Long)
}