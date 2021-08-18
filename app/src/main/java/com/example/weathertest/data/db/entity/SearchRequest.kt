package com.example.weathertest.data.db.entity

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class SearchRequest(
    @PrimaryKey
    var id: String = "",
    var name: String = "",
    var lat: Long = 0,
    var lon: Long = 0
) : RealmObject()