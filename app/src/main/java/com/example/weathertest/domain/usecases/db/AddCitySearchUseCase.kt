package com.example.weathertest.domain.usecases.db

import com.example.weathertest.domain.model.City
import com.example.weathertest.domain.repository.DataBaseRepository

class AddCitySearchUseCase(private val dbRepository: DataBaseRepository) {

    operator fun invoke(city: City) {
        dbRepository.addSearch(city)
    }
}