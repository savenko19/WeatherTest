package com.example.weathertest.data.repository

import android.util.Log
import com.example.weathertest.data.db.entity.SearchRequest
import com.example.weathertest.data.db.provider.DataBaseProvider
import com.example.weathertest.domain.model.City
import com.example.weathertest.domain.repository.DataBaseRepository


class DataBaseRepositoryImpl(dbProvider: DataBaseProvider) : DataBaseRepository {

    private val mRealm = dbProvider.getRealm()


    override fun addSearch(city: City) {
        val id = "${city.coordinates.first()}${city.coordinates.last()}"
        var mCity =
            mRealm!!.where(SearchRequest::class.java)
                .equalTo(
                    "id",
                    id
                ).findFirst()

        mRealm.beginTransaction()
        if (mCity == null) {
            mCity = mRealm.createObject(SearchRequest::class.java, id)
            mCity.name = city.name
            mCity.lat = city.coordinates.first()
            mCity.lon = city.coordinates.last()
        }

        Log.e("Test", "City entity: $mCity")
        mRealm.commitTransaction()
    }

    override fun getSearchHistory(): ArrayList<City>? {
        val searchCities = mRealm?.where(SearchRequest::class.java)?.findAll()

        if (searchCities != null) {
            val searchHistory = ArrayList<City>()

            for (city in searchCities) {

                searchHistory.add(
                    City(
                        city.name,
                        java.util.ArrayList(
                            listOf(
                                city.lat,
                                city.lon
                            )
                        )
                    )
                )
            }
            return searchHistory
        } else {
            return null
        }
    }
}