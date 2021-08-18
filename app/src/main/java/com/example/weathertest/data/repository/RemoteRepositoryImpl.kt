package com.example.weathertest.data.repository

import android.util.Log
import com.example.weathertest.data.mapper.WeatherMapper
import com.example.weathertest.data.remote.api.WeatherApi
import com.example.weathertest.domain.model.WeatherOfCity
import com.example.weathertest.domain.repository.RemoteRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

private const val TAG = "RemoteRepository"

class RemoteRepositoryImpl(private val api: WeatherApi) : RemoteRepository {

    override fun getWeather(lat: Long, lon: Long): Single<WeatherOfCity> =
        Single.create { weatherEmitter ->
            api.getWeather(lat, lon)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Log.e(TAG, "HOURS: ${it.forecasts[0].hours}")
                    weatherEmitter.onSuccess(WeatherMapper().map(it))
                }, {
                    Log.e(TAG, "Failure get: ${it.localizedMessage}")
                })
        }
}