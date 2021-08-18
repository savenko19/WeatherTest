package com.example.weathertest.ui.main.presenter

import android.content.Context
import com.example.weathertest.App
import com.example.weathertest.data.repository.DataBaseRepositoryImpl
import com.example.weathertest.domain.repository.DataBaseRepository
import com.example.weathertest.domain.usecases.db.AddCitySearchUseCase
import com.example.weathertest.domain.usecases.db.GetSearchHistoryUseCase

class MainPresenterFactory {

    companion object {
        fun createPresenter(context: Context): MainPresenter {

            val dbProvider = App.getDbProvider(context)!!
            val dbRepository: DataBaseRepository = DataBaseRepositoryImpl(dbProvider)
            val addCitySearchUseCase = AddCitySearchUseCase(dbRepository)
            val getSearchHistoryUseCase = GetSearchHistoryUseCase(dbRepository)
            return MainPresenterImpl(addCitySearchUseCase, getSearchHistoryUseCase)
        }
    }
}