package com.example.weathertest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RemoteForecastItem(
    val date: String,
    @SerializedName("week")
    val numberOfWeek: Int,
    @SerializedName("temp")
    val averageTemp: Int,
    val hours: ArrayList<RemoteHourItem>,
    @SerializedName("parts")
    val parts: RemotePart
)

data class RemoteHourItem(
    val hour: String,
    val temp: Int
)

data class RemotePart(
    @SerializedName("night")
    val night: Day,
    @SerializedName("day")
    val day: Day
)


data class Day(
    @SerializedName("temp_avg")
    val averageTemp: Int
)