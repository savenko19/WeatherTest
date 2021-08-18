package com.example.weathertest.ui.city.presenter

import android.content.Context
import com.example.weathertest.App
import com.example.weathertest.data.repository.RemoteRepositoryImpl
import com.example.weathertest.domain.repository.RemoteRepository
import com.example.weathertest.domain.usecases.remote.GetWeatherUseCase

class CityPresenterFactory {

    companion object {
        fun createPresenter(context: Context): CityPresenter {

            val remoteRepository: RemoteRepository = RemoteRepositoryImpl(App.getApi(context)!!)
            val getWeatherUseCase = GetWeatherUseCase(remoteRepository)
            return CityPresenterImpl(getWeatherUseCase)
        }
    }
}