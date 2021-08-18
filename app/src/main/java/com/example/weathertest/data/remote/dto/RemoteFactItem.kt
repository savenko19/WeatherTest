package com.example.weathertest.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RemoteFactItem(
    val temp: Int,
    @SerializedName("feels_like")
    val fakeTemp: Int,
    @SerializedName("temp_water")
    val tempWater: Int,
    @SerializedName("icon")
    val icon: String,
    val condition: String,
)