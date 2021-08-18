package com.example.weathertest.ui.main.presenter

import com.example.weathertest.domain.model.City
import com.example.weathertest.domain.usecases.db.AddCitySearchUseCase
import com.example.weathertest.domain.usecases.db.GetSearchHistoryUseCase
import com.example.weathertest.ui.main.view.MainView
import com.example.weathertest.ui.mvp.BasePresenter

class MainPresenterImpl(
    private val addCity: AddCitySearchUseCase,
    private val getSearchHistory: GetSearchHistoryUseCase
) : BasePresenter<MainView>(), MainPresenter {

    override fun viewIsReady() {
        view?.showCities()
        view?.showSearchHistory(getSearchHistory())
    }

    override fun addCitySearch(city: City) {
        addCity(city)
    }
}