package com.example.weathertest.ui.main.presenter

import com.example.weathertest.domain.model.City
import com.example.weathertest.ui.main.view.MainView
import com.example.weathertest.ui.mvp.MvpPresenter

interface MainPresenter : MvpPresenter<MainView> {

    fun addCitySearch(city: City)
}