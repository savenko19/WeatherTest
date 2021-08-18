package com.example.weathertest.data.remote.dto

import com.example.weathertest.data.remote.RemoteInfoItem
import com.google.gson.annotations.SerializedName

data class RemoteWeather(
    val now: Int,
    @SerializedName("now_dt")
    val nowDt: String,
    val info: RemoteInfoItem,
    val fact: RemoteFactItem,
    val forecasts: ArrayList<RemoteForecastItem>
)