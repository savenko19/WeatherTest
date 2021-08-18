package com.example.weathertest.utils

fun formatTemp(temp: Int): String {
    return when (temp > 0) {
        true -> {
            "+$temp"
        }
        false -> {
            "$temp"
        }
    }
}

fun formatHour(hour: Int) =
    when (hour > 9) {
        true -> {"$hour:00"}
        false -> {"0$hour:00"}
    }
