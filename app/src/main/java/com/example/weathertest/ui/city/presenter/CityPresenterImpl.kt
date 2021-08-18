package com.example.weathertest.ui.city.presenter

import com.example.weathertest.domain.usecases.remote.GetWeatherUseCase
import com.example.weathertest.ui.city.view.CityView
import com.example.weathertest.ui.mvp.BasePresenter
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class CityPresenterImpl(
    private val getWeather: GetWeatherUseCase
) : BasePresenter<CityView>(), CityPresenter {

    override fun viewIsReady() {

    }

    override fun getWeatherOfCity(lat: Long, lon: Long) {
        getWeather(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                view?.showWeather(it)
            }, {

            })
    }
}