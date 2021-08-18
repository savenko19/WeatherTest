package com.example.weathertest.ui.main.view.adapter

interface OnEmptySearchListener {

    fun onEmptySearch(
        lat: Long,
        lon: Long,
        isEmpty: Boolean)

}