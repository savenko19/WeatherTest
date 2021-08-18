package com.example.weathertest.data.remote

import com.google.gson.annotations.SerializedName

data class RemoteInfoItem(
    val lat: Long,
    val lon: Long,
    @SerializedName("tzinfo")
    val timeZoneInfo: TimeZoneInfo,
    @SerializedName("def_pressure_mm")
    val defPressureMm: Long,
    @SerializedName("def_pressure_pa")
    val defPressurePa: Long,
    val url: String
)

data class TimeZoneInfo(
    val offset: Long,
    @SerializedName("name")
    val timeZoneName: String,
    @SerializedName("abbr")
    val timeZoneAbbr: String,
    val dst: Boolean
)