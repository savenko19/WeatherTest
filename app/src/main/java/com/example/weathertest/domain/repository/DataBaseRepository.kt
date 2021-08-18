package com.example.weathertest.domain.repository

import com.example.weathertest.domain.model.City

interface DataBaseRepository {

    fun addSearch(city: City)

    fun getSearchHistory(): ArrayList<City>?
}