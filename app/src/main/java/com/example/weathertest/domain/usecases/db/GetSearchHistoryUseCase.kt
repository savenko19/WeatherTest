package com.example.weathertest.domain.usecases.db

import com.example.weathertest.domain.repository.DataBaseRepository

class GetSearchHistoryUseCase(private val dbRepository: DataBaseRepository) {

    operator fun invoke() = dbRepository.getSearchHistory()
}