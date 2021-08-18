package com.example.weathertest

import android.app.Application
import android.content.Context
import com.example.weathertest.data.db.provider.DataBaseProvider
import com.example.weathertest.data.remote.api.WeatherApi
import com.example.weathertest.data.remote.provider.RetrofitProvider

class App : Application() {

    private var retrofitProvider: RetrofitProvider? = null
    private var dbProvider: DataBaseProvider? = null

    companion object {
        private fun getApp(context: Context) = context.applicationContext as App
        fun getDbProvider(context: Context) = getApp(context).dbProvider
        fun getApi(context: Context) =
            getApp(context).retrofitProvider?.getRetrofit()?.create(WeatherApi::class.java)
    }

    override fun onCreate() {
        super.onCreate()
        retrofitProvider = RetrofitProvider()
        dbProvider = DataBaseProvider(this)
    }
}