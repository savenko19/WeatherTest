package com.example.weathertest.ui.main.view

import com.example.weathertest.domain.model.City
import com.example.weathertest.ui.mvp.MvpView

interface MainView : MvpView {

    fun showCities()

    fun showSearchHistory(searchCities: ArrayList<City>?)
}