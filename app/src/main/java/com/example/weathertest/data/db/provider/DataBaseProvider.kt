package com.example.weathertest.data.db.provider

import android.content.Context
import io.realm.Realm
import io.realm.RealmConfiguration




class DataBaseProvider(context: Context) {

    private var mRealm: Realm? = null

    init {
        Realm.init(context)
        val mRealmConfiguration = RealmConfiguration.Builder().name("weather.realm").build()
        Realm.setDefaultConfiguration(mRealmConfiguration)
        mRealm = Realm.getDefaultInstance()
    }

    fun getRealm(): Realm? {
        return mRealm
    }

}